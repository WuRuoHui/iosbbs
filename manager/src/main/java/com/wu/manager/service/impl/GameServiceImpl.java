package com.wu.manager.service.impl;

import com.github.pagehelper.PageHelper;
import com.wu.common.utils.LayUIResult;
import com.wu.manager.dto.GameContactDTO;
import com.wu.manager.dto.GameDTO;
import com.wu.manager.dto.GameDownloadDTO;
import com.wu.manager.enums.CustomizeErrorCode;
import com.wu.manager.mapper.*;
import com.wu.manager.pojo.*;
import com.wu.manager.service.GameService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: iosbbs
 * @description: 游戏service实现类
 * @author: Wu
 * @create: 2020-02-01 00:10
 **/

@Service
public class GameServiceImpl implements GameService {

    @Autowired
    private GameMapper gameMapper;
    @Autowired
    private DeptMapper deptMapper;
    @Autowired
    private GameDownloadMapper gameDownloadMapper;
    @Autowired
    private GameExtMapper gameExtMapper;
    @Autowired
    private GameContactMapper gameContactMapper;

    @Override
    public LayUIResult insertGame(Game game) {
        //判断游戏名是否已存在
        GameExample gameExample = new GameExample();
        GameExample.Criteria criteria = gameExample.createCriteria().andNameEqualTo(game.getName());
        //如果为更新操作，则当前更新行应该排除
        if (game.getId() != null) {
            criteria.andIdNotEqualTo(game.getId());
        }
        List<Game> games = gameMapper.selectByExample(gameExample);
        if (games != null && games.size() > 0) {
            return LayUIResult.build(1, CustomizeErrorCode.GAME_ALREADY_EXIST.getMessage());
        }
        //当id不为空时为更新操作
        if (game.getId() != null) {
            game.setGmtModify(System.currentTimeMillis());
            int rows = gameMapper.updateByPrimaryKeySelective(game);
            if (rows > 0) {
                return LayUIResult.build(0, CustomizeErrorCode.UPDATE_DATA_SUCCESS.getMessage());
            }
            return LayUIResult.build(1, CustomizeErrorCode.UPDATE_DATA_FAIL.getMessage());
        }
        //插入新数据操作
        game.setGmtCreate(System.currentTimeMillis());
        game.setGmtModify(System.currentTimeMillis());
        int rows = gameMapper.insertSelective(game);
        if (rows > 0) {
            return LayUIResult.build(0, "添加成功！");
        }
        return LayUIResult.fail("添加失败");
    }

    @Override
    public LayUIResult selectMainGames() {
        GameExample gameExample = new GameExample();
        gameExample.createCriteria().andIsParentEqualTo(true);
        List<Game> mainGames = gameMapper.selectByExample(gameExample);
        if (mainGames != null && mainGames.size() > 0) {
            return LayUIResult.build(0, "success", mainGames);
        }
        return LayUIResult.fail("fail");
    }

    @Override
    public LayUIResult selectAllGames(String nameSearch, Integer page, Integer limit) {
        List<GameDTO> gameDTOS = new ArrayList<>();
        GameExample gameExample = new GameExample();
        if (!StringUtils.isEmpty(nameSearch)) {
            gameExample.createCriteria()
                    .andNameLike("%" + nameSearch.trim() + "%");
        }
        if (page != null && limit != null) {
            PageHelper.startPage(page, limit);
        }
        List<Game> games = gameMapper.selectByExample(gameExample);
        if (games != null && games.size() > 0) {
            long count = gameMapper.countByExample(gameExample);
            for (Game game : games) {
                GameDTO gameDTO = new GameDTO();
                BeanUtils.copyProperties(game, gameDTO);
                Dept dept = deptMapper.selectByPrimaryKey(game.getDeptId());
                gameDTO.setDept(dept);
                if (!game.getIsParent() && game.getParentId() != null) {
                    Game parentGame = gameMapper.selectByPrimaryKey(game.getParentId());
                    gameDTO.setParent(parentGame);
                }
                gameDTOS.add(gameDTO);
            }
            return LayUIResult.build(0, (int) count, "success", gameDTOS);
        }
        return LayUIResult.fail("fail");
    }

    @Override
    public LayUIResult deleteGameById(Integer id) {
        if (id == null) {
            return LayUIResult.build(1, CustomizeErrorCode.NOT_ROW_SELECT.getMessage());
        }
        Game game = gameMapper.selectByPrimaryKey(id);
        if (game == null) {
            return LayUIResult.build(1, CustomizeErrorCode.GAME_NOT_FOUND.getMessage());
        }
        int rows = gameMapper.deleteByPrimaryKey(id);
        if (rows > 0) {
            return LayUIResult.build(0, CustomizeErrorCode.DELETE_DATA_SUCCESS.getMessage());
        }
        return LayUIResult.build(1, CustomizeErrorCode.DELETE_DATA_FAIL.getMessage());
    }

    @Override
    @Transactional
    public LayUIResult deleteGamesByIds(List<Integer> ids) {
        if (ids == null || ids.size() < 1) {
            return LayUIResult.build(1, CustomizeErrorCode.NOT_ROW_SELECT.getMessage());
        }
        for (Integer id : ids) {
            int rows = gameMapper.deleteByPrimaryKey(id);
            if (rows < 1) {
                return LayUIResult.build(1, CustomizeErrorCode.DELETE_DATA_FAIL.getMessage());
            }
        }
        return LayUIResult.build(0, CustomizeErrorCode.DELETE_DATA_SUCCESS.getMessage());
    }

    @Override
    public LayUIResult insertOrUpdateGameDownload(GameDownload gameDownload) {
        if (gameDownload == null) {
            return LayUIResult.build(1, CustomizeErrorCode.INSERT_DATA_NOT_FILL.getMessage());
        }
        //判断是否已经存在了某个游戏的下载方式
        GameDownloadExample gameDownloadExample = new GameDownloadExample();
        GameDownloadExample.Criteria criteria = gameDownloadExample.createCriteria()
                .andGameIdEqualTo(gameDownload.getGameId());
        if (gameDownload.getId() != null) {
            criteria.andIdNotEqualTo(gameDownload.getId());
        }
        List<GameDownload> gameDownloads = gameDownloadMapper.selectByExample(gameDownloadExample);
        if (gameDownloads != null && gameDownloads.size() > 0) {
            return LayUIResult.build(1, CustomizeErrorCode.DATA_ALREADY_EXIST.getMessage());
        }
        //如ID不为空，则进行更新操作
        if (gameDownload.getId() != null) {
            int rows = gameDownloadMapper.updateByPrimaryKeySelective(gameDownload);
            if (rows > 0) {
                return LayUIResult.build(0, CustomizeErrorCode.UPDATE_DATA_SUCCESS.getMessage());
            }
            return LayUIResult.build(1, CustomizeErrorCode.UPDATE_DATA_FAIL.getMessage());
        }
        //添加操作
        int rows = gameDownloadMapper.insertSelective(gameDownload);
        if (rows > 0) {
            return LayUIResult.build(0, CustomizeErrorCode.INSERT_DATA_SUCCESS.getMessage());
        }
        return LayUIResult.build(1, CustomizeErrorCode.INSERT_DATA_FAIL.getMessage());
    }

    @Override
    public LayUIResult selectAllGameDownloads(String nameSearch, Integer page, Integer limit) {
        List<GameDownloadDTO> gameDownloadDTOS = new ArrayList<>();
        GameDownloadExample gameDownloadExample = new GameDownloadExample();
        if (!StringUtils.isEmpty(nameSearch)) {
            List<Integer> ids = gameExtMapper.selectIdsByNameSearch(nameSearch.trim());
            if (ids == null || ids.size() < 10) {
                ids.add(-1);
            }
            gameDownloadExample.createCriteria().andGameIdIn(ids);
        }
        PageHelper.startPage(page, limit);     //设置分页
        List<GameDownload> gameDownloads = gameDownloadMapper.selectByExample(gameDownloadExample);
        if (gameDownloads != null && gameDownloads.size() > 0) {
            long count = gameDownloadMapper.countByExample(gameDownloadExample);    //查询总记录数
            for (GameDownload gameDownload : gameDownloads) {
                GameDownloadDTO gameDownloadDTO = new GameDownloadDTO();
                BeanUtils.copyProperties(gameDownload, gameDownloadDTO);
                Game game = gameMapper.selectByPrimaryKey(gameDownload.getGameId());
                gameDownloadDTO.setGame(game);
                gameDownloadDTOS.add(gameDownloadDTO);
            }
            return LayUIResult.build(0, (int) count, CustomizeErrorCode.SELECT_DATA_SUCCESS.getMessage(), gameDownloadDTOS);
        }
        return LayUIResult.build(1, CustomizeErrorCode.NOT_DATA_EXIST.getMessage());
    }

    @Override
    public LayUIResult deleteGameDownloadById(Integer id) {
        if (id == null) {
            return LayUIResult.build(1, CustomizeErrorCode.NOT_ROW_SELECT.getMessage());
        }
        //判断要删除的数据是否存在
        //如不存在，则返回错误信息
        GameDownload gameDownload = gameDownloadMapper.selectByPrimaryKey(id);
        if (gameDownload == null) {
            return LayUIResult.build(1, CustomizeErrorCode.DATA_NOT_FOUND.getMessage());
        }
        //存在则删除
        int rows = gameDownloadMapper.deleteByPrimaryKey(id);
        if (rows > 0) {
            return LayUIResult.build(0, CustomizeErrorCode.DELETE_DATA_SUCCESS.getMessage());
        }
        return LayUIResult.build(1, CustomizeErrorCode.DELETE_DATA_FAIL.getMessage());
    }

    @Override
    public LayUIResult deleteGameDownloadByIds(List<Integer> gameDownloadIds) {
        if (gameDownloadIds == null || gameDownloadIds.size() < 1) {
            return LayUIResult.build(1, CustomizeErrorCode.NOT_ROW_SELECT.getMessage());
        }
        for (Integer gameDownloadId : gameDownloadIds) {
            int rows = gameDownloadMapper.deleteByPrimaryKey(gameDownloadId);
            if (rows < 1) {
                return LayUIResult.build(1, CustomizeErrorCode.DELETE_DATA_FAIL.getMessage());
            }
        }
        return LayUIResult.build(0, CustomizeErrorCode.DELETE_DATA_SUCCESS.getMessage());
    }

    @Override
    public LayUIResult insertGameContact(GameContact gameContact) {
        if (gameContact == null) {
            return LayUIResult.build(1, CustomizeErrorCode.INSERT_DATA_NOT_FILL.getMessage());
        }
        //判断联系方式是否已存在
        GameContactExample gameContactExample = new GameContactExample();
        gameContactExample.createCriteria()
                .andGameIdEqualTo(gameContact.getGameId());
        long rows = gameContactMapper.countByExample(gameContactExample);
        //存在则返回已存在信息
        if (rows > 0) {
            return LayUIResult.build(1, CustomizeErrorCode.DATA_ALREADY_EXIST.getMessage());
        }
        //不存在则添加
        int successRow = gameContactMapper.insertSelective(gameContact);
        if (successRow > 0) {
            return LayUIResult.build(0, CustomizeErrorCode.INSERT_DATA_SUCCESS.getMessage());
        }
        return LayUIResult.build(1, CustomizeErrorCode.INSERT_DATA_FAIL.getMessage());
    }

    @Override
    public LayUIResult selectAllGameContacts(String nameSearch, Integer page, Integer limit) {
        GameContactExample gameContactExample = new GameContactExample();
        GameContactExample.Criteria gameContactExampleCriteria = gameContactExample.createCriteria();
        if (!StringUtils.isEmpty(nameSearch)) {
            List<Integer> gameIds = gameExtMapper.selectIdsByNameSearch(nameSearch.trim());
            if (gameIds != null && gameIds.size() > 0) {
                gameContactExampleCriteria.andGameIdIn(gameIds);
            }
        }
        if (page != null && limit != null) {
            PageHelper.startPage(page, limit);
        }
        List<GameContact> gameContacts = gameContactMapper.selectByExample(gameContactExample);
        List<GameContactDTO> gameContactDTOS = new ArrayList<>();
        if (gameContacts != null && gameContacts.size() > 0) {
            long count = gameContactMapper.countByExample(gameContactExample);
            for (GameContact gameContact : gameContacts) {
                GameContactDTO gameContactDTO = new GameContactDTO();
                BeanUtils.copyProperties(gameContact, gameContactDTO);
                if (gameContact.getGameId() != null) {
                    Game game = gameMapper.selectByPrimaryKey(gameContact.getGameId());
                    GameDTO gameDTO = new GameDTO();
                    if (game != null) {
                        BeanUtils.copyProperties(game, gameDTO);
                    }
                    if (game.getDeptId() != null) {
                        Dept dept = deptMapper.selectByPrimaryKey(game.getDeptId());
                        gameDTO.setDept(dept);
                    }
                    gameContactDTO.setGame(gameDTO);
                }
                gameContactDTOS.add(gameContactDTO);
            }
            return LayUIResult.build(0, (int) count, CustomizeErrorCode.SELECT_DATA_SUCCESS.getMessage(), gameContactDTOS);
        }
        return LayUIResult.build(1, CustomizeErrorCode.NOT_DATA_EXIST.getMessage());
    }

    @Override
    public LayUIResult deleteGameContactById(Integer id) {
        if (id == null) {
            return LayUIResult.build(1, CustomizeErrorCode.NOT_ROW_SELECT.getMessage());
        }
        //判断当前数据库是否存在该条数据
        GameContact gameContact = gameContactMapper.selectByPrimaryKey(id);
        if (gameContact == null) {
            return LayUIResult.build(1, CustomizeErrorCode.DATA_NOT_FOUND.getMessage());
        }
        int rows = gameContactMapper.deleteByPrimaryKey(id);
        if (rows > 0) {
            return LayUIResult.build(0, CustomizeErrorCode.DELETE_DATA_SUCCESS.getMessage());
        }
        return LayUIResult.build(1, CustomizeErrorCode.DELETE_DATA_FAIL.getMessage());
    }

    @Override
    @Transactional
    public LayUIResult deleteGameContactByGameContactIds(List<Integer> gameContactIds) {
        if (gameContactIds == null || gameContactIds.size() < 1) {
            return LayUIResult.build(1, CustomizeErrorCode.NOT_ROW_SELECT.getMessage());
        }
        for (Integer gameContactId : gameContactIds) {
            int rows = gameContactMapper.deleteByPrimaryKey(gameContactId);
            if (rows < 1) {
                return LayUIResult.build(1, CustomizeErrorCode.DELETE_DATA_FAIL.getMessage());
            }
        }
        return LayUIResult.build(0, CustomizeErrorCode.DELETE_DATA_SUCCESS.getMessage());
    }

    @Override
    public LayUIResult updateGameContact(GameContact gameContact) {
        if (gameContact == null) {
            return LayUIResult.build(1, CustomizeErrorCode.UPDATE_DATA_FAIL.getMessage());
        }
        //当更新的游戏发生变化，判断数据库中是否已存在该游戏的联系方式
        GameContactExample gameContactExample = new GameContactExample();
        GameContactExample.Criteria criteria = gameContactExample.createCriteria();
        //判断条件为等于当前游戏id且不为传入的数据的主键
        criteria.andIdNotEqualTo(gameContact.getId());
        if (gameContact.getGameId() != null) {
            criteria.andGameIdEqualTo(gameContact.getGameId());
        }
        List<GameContact> gameContacts = gameContactMapper.selectByExample(gameContactExample);
        if (gameContacts != null && gameContacts.size() > 0) {
            return LayUIResult.build(1, CustomizeErrorCode.UPDATE_FAIL_DATA_EXIST.getMessage());
        }
        int rows = gameContactMapper.updateByPrimaryKeySelective(gameContact);
        if (rows > 0) {
            return LayUIResult.build(0, CustomizeErrorCode.UPDATE_DATA_SUCCESS.getMessage());
        }
        return LayUIResult.build(1, CustomizeErrorCode.UPDATE_DATA_FAIL.getMessage());
    }

}
