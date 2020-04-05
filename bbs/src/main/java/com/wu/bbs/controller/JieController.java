package com.wu.bbs.controller;

import com.wu.bbs.DTO.JieDTO;
import com.wu.bbs.DTO.ReplyDTO;
import com.wu.bbs.pojo.Jie;
import com.wu.bbs.pojo.Reply;
import com.wu.bbs.service.JieService;
import com.wu.common.enums.impl.CustomizeJieTypeCode;
import com.wu.common.utils.LayUIResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@PreAuthorize("hasAnyRole('ROLE_USER','ROLE_MANAGER')")
public class JieController {

    @Autowired
    private JieService jieService;

    @RequestMapping("/jie/add")
    public String jieAdd() {
        return "jie/add";
    }

    @RequestMapping("/jie/edit/{jieId}")
    public String jieDetail(@PathVariable (name = "jieId") Integer jieId, Model model) {
        JieDTO jieDTO = jieService.selectJieById(jieId);
        model.addAttribute("jie",jieDTO);
        return "jie/edit";
    }

    @RequestMapping(value = "/jie/quiz",method = RequestMethod.GET)
    public String selectQuizJie(Model model) {
        List<JieDTO> jieDTOS = jieService.selectJieByColumnIdAndCurr(CustomizeJieTypeCode.JIE_JIE.getCode(),1);
        Integer jieCount = jieService.countJieByColumnId(CustomizeJieTypeCode.JIE_JIE.getCode());
        model.addAttribute("jieList",jieDTOS);
        model.addAttribute("jieCount",jieCount);
        return "jie/index";
    }

    @RequestMapping(value = "/jie/quiz/page/{curr}",method = RequestMethod.GET)
    public String selectQuizJieWithCurr(@PathVariable (name = "curr") Integer curr,Model model) {
        List<JieDTO> jieDTOS = jieService.selectJieByColumnIdAndCurr(CustomizeJieTypeCode.JIE_JIE.getCode(),curr);
        Integer jieCount = jieService.countJieByColumnId(CustomizeJieTypeCode.JIE_JIE.getCode());
        model.addAttribute("jieList",jieDTOS);
        model.addAttribute("jieCount",jieCount);
        return "/jie/index";
    }

    @RequestMapping(value = "/jie/quiz/{status}",method = RequestMethod.GET)
    public String selectQuizJieWithStatus(@PathVariable(name = "status")String status, Model model) {
        List<JieDTO> jieDTOS = jieService.selectJieByColumnIdAndStatusAndCurr(CustomizeJieTypeCode.JIE_JIE.getCode(),status,1);
        Integer jieCount = jieService.countJieByColumnIdAndStatus(CustomizeJieTypeCode.JIE_JIE.getCode(),status);
        model.addAttribute("jieList",jieDTOS);
        model.addAttribute("jieCount",jieCount);
        return "jie/index";
    }

    @RequestMapping(value = "/jie/quiz/{status}/page/{curr}",method = RequestMethod.GET)
    public String selectQuizJieWithStatus(
            @PathVariable(name = "status")String status,
            @PathVariable(name="curr") Integer curr,
            Model model) {
        List<JieDTO> jieDTOS = jieService.selectJieByColumnIdAndStatusAndCurr(CustomizeJieTypeCode.JIE_JIE.getCode(),status,curr);
        Integer jieCount = jieService.countJieByColumnIdAndStatus(CustomizeJieTypeCode.JIE_JIE.getCode(),status);
        model.addAttribute("jieList",jieDTOS);
        model.addAttribute("jieCount",jieCount);
        return "jie/index";
    }

    @RequestMapping(value = "/jie/share",method = RequestMethod.GET)
    public String selectShareJie(Model model) {
        List<JieDTO> jieDTOS = jieService.selectJieByColumnIdAndCurr(CustomizeJieTypeCode.JIE_SHARE.getCode(),1);
        Integer jieCount = jieService.countJieByColumnId(CustomizeJieTypeCode.JIE_SHARE.getCode());
        model.addAttribute("jieList",jieDTOS);
        model.addAttribute("jieCount",jieCount);
        return "jie/index";
    }

    @RequestMapping(value = "/jie/share/page/{curr}",method = RequestMethod.GET)
    public String selectShareJieWithCurr(@PathVariable(name = "curr") Integer curr, Model model) {
        List<JieDTO> jieDTOS = jieService.selectJieByColumnIdAndCurr(CustomizeJieTypeCode.JIE_SHARE.getCode(),curr);
        Integer jieCount = jieService.countJieByColumnId(CustomizeJieTypeCode.JIE_SHARE.getCode());
        model.addAttribute("jieList",jieDTOS);
        model.addAttribute("jieCount",jieCount);
        return "jie/index";
    }

    @RequestMapping(value = "/jie/share/{status}",method = RequestMethod.GET)
    public String selectShareJieWithStatus(@PathVariable(name = "status")String status, Model model) {
        List<JieDTO> jieDTOS = jieService.selectJieByColumnIdAndStatusAndCurr(CustomizeJieTypeCode.JIE_SHARE.getCode(),status,1);
        Integer jieCount = jieService.countJieByColumnIdAndStatus(CustomizeJieTypeCode.JIE_SHARE.getCode(),status);
        model.addAttribute("jieCount",jieCount);
        model.addAttribute("jieList",jieDTOS);
        return "jie/index";
    }

    @RequestMapping(value = "/jie/share/{status}/page/{curr}",method = RequestMethod.GET)
    public String selectShareJieWithStatus(
            @PathVariable(name = "status")String status,
            @PathVariable(name="curr")Integer curr,
            Model model) {
        List<JieDTO> jieDTOS = jieService.selectJieByColumnIdAndStatusAndCurr(CustomizeJieTypeCode.JIE_SHARE.getCode(),status,curr);
        Integer jieCount = jieService.countJieByColumnIdAndStatus(CustomizeJieTypeCode.JIE_SHARE.getCode(),status);
        model.addAttribute("jieCount",jieCount);
        model.addAttribute("jieList",jieDTOS);
        return "jie/index";
    }

    @RequestMapping(value = "/jie/discussion",method = RequestMethod.GET)
    public String selectDiscussionJie(Model model) {
        List<JieDTO> jieDTOS = jieService.selectJieByColumnIdAndCurr(CustomizeJieTypeCode.JIE_DISCUSSION.getCode(),1);
        Integer jieCount = jieService.countJieByColumnId(CustomizeJieTypeCode.JIE_DISCUSSION.getCode());
        model.addAttribute("jieCount",jieCount);
        model.addAttribute("jieList",jieDTOS);
        return "jie/index";
    }

    @RequestMapping(value = "/jie/discussion/page/{curr}",method = RequestMethod.GET)
    public String selectDiscussionJie(@PathVariable(name = "curr") Integer curr, Model model) {
        List<JieDTO> jieDTOS = jieService.selectJieByColumnIdAndCurr(CustomizeJieTypeCode.JIE_DISCUSSION.getCode(),curr);
        Integer jieCount = jieService.countJieByColumnId(CustomizeJieTypeCode.JIE_DISCUSSION.getCode());
        model.addAttribute("jieCount",jieCount);
        model.addAttribute("jieList",jieDTOS);
        return "jie/index";
    }

    @RequestMapping(value = "/jie/discussion/{status}",method = RequestMethod.GET)
    public String selectDiscussionJieWithStatus(@PathVariable(name = "status")String status, Model model) {
        List<JieDTO> jieDTOS = jieService.selectJieByColumnIdAndStatusAndCurr(CustomizeJieTypeCode.JIE_DISCUSSION.getCode(),status,1);
        Integer jieCount = jieService.countJieByColumnIdAndStatus(CustomizeJieTypeCode.JIE_DISCUSSION.getCode(),status);
        model.addAttribute("jieCount",jieCount);
        model.addAttribute("jieList",jieDTOS);
        return "jie/index";
    }

    @RequestMapping(value = "/jie/discussion/{status}/page/{curr}",method = RequestMethod.GET)
    public String selectDiscussionJieWithStatus(
            @PathVariable(name = "status")String status,
            @PathVariable(name = "curr") Integer curr,
            Model model) {
        List<JieDTO> jieDTOS = jieService.selectJieByColumnIdAndStatusAndCurr(CustomizeJieTypeCode.JIE_DISCUSSION.getCode(),status,curr);
        Integer jieCount = jieService.countJieByColumnIdAndStatus(CustomizeJieTypeCode.JIE_DISCUSSION.getCode(),status);
        model.addAttribute("jieCount",jieCount);
        model.addAttribute("jieList",jieDTOS);
        return "jie/index";
    }

    @RequestMapping(value = "/jie/advice",method = RequestMethod.GET)
    public String selectAdviceJie(Model model) {
        List<JieDTO> jieDTOS = jieService.selectJieByColumnIdAndCurr(CustomizeJieTypeCode.JIE_ADVICE.getCode(),1);
        Integer jieCount = jieService.countJieByColumnId(CustomizeJieTypeCode.JIE_ADVICE.getCode());
        model.addAttribute("jieCount",jieCount);
        model.addAttribute("jieList",jieDTOS);
        return "jie/index";
    }

    @RequestMapping(value = "/jie/advice/page/{curr}",method = RequestMethod.GET)
    public String selectAdviceJie(@PathVariable (name = "curr") Integer curr, Model model) {
        List<JieDTO> jieDTOS = jieService.selectJieByColumnIdAndCurr(CustomizeJieTypeCode.JIE_ADVICE.getCode(),curr);
        Integer jieCount = jieService.countJieByColumnId(CustomizeJieTypeCode.JIE_ADVICE.getCode());
        model.addAttribute("jieCount",jieCount);
        model.addAttribute("jieList",jieDTOS);
        return "jie/index";
    }

    @RequestMapping(value = "/jie/advice/{status}",method = RequestMethod.GET)
    public String selectAdviceJieWithStatus(@PathVariable(name = "status")String status, Model model) {
        List<JieDTO> jieDTOS = jieService.selectJieByColumnIdAndStatusAndCurr(CustomizeJieTypeCode.JIE_ADVICE.getCode(),status,1);
        Integer jieCount = jieService.countJieByColumnIdAndStatus(CustomizeJieTypeCode.JIE_ADVICE.getCode(),status);
        model.addAttribute("jieCount",jieCount);
        model.addAttribute("jieList",jieDTOS);
        return "jie/index";
    }

    @RequestMapping(value = "/jie/advice/{status}/page/{curr}",method = RequestMethod.GET)
    public String selectAdviceJieWithStatus(
            @PathVariable(name = "status")String status,
            @PathVariable(name = "curr") Integer curr,
            Model model) {
        List<JieDTO> jieDTOS = jieService.selectJieByColumnIdAndStatusAndCurr(CustomizeJieTypeCode.JIE_ADVICE.getCode(),status,curr);
        Integer jieCount = jieService.countJieByColumnIdAndStatus(CustomizeJieTypeCode.JIE_ADVICE.getCode(),status);
        model.addAttribute("jieCount",jieCount);
        model.addAttribute("jieList",jieDTOS);
        return "jie/index";
    }

    @RequestMapping(value = "/jie/notice",method = RequestMethod.GET)
    public String selectNoticeJie(Model model) {
        List<JieDTO> jieDTOS = jieService.selectJieByColumnIdAndCurr(CustomizeJieTypeCode.JIE_NOTICE.getCode(),1);
        Integer jieCount = jieService.countJieByColumnId(CustomizeJieTypeCode.JIE_NOTICE.getCode());
        model.addAttribute("jieCount",jieCount);
        model.addAttribute("jieList",jieDTOS);
        return "jie/index";
    }

    @RequestMapping(value = "/jie/notice/page/{curr}",method = RequestMethod.GET)
    public String selectNoticeJie(@PathVariable(name = "curr")Integer curr, Model model) {
        List<JieDTO> jieDTOS = jieService.selectJieByColumnIdAndCurr(CustomizeJieTypeCode.JIE_NOTICE.getCode(),1);
        Integer jieCount = jieService.countJieByColumnId(CustomizeJieTypeCode.JIE_NOTICE.getCode());
        model.addAttribute("jieCount",jieCount);
        model.addAttribute("jieList",jieDTOS);
        return "jie/index";
    }

    @RequestMapping(value = "/jie/notice/{status}",method = RequestMethod.GET)
    public String selectNoticeJieWithStatus(@PathVariable(name = "status")String status, Model model) {
        List<JieDTO> jieDTOS = jieService.selectJieByColumnIdAndStatusAndCurr(CustomizeJieTypeCode.JIE_NOTICE.getCode(),status,1);
        Integer jieCount = jieService.countJieByColumnIdAndStatus(CustomizeJieTypeCode.JIE_NOTICE.getCode(),status);
        model.addAttribute("jieCount",jieCount);
        model.addAttribute("jieList",jieDTOS);
        return "jie/index";
    }

    @RequestMapping(value = "/jie/notice/{status}/page/{curr}",method = RequestMethod.GET)
    public String selectNoticeJieWithStatus(
            @PathVariable(name = "status")String status,
            @PathVariable(name = "curr")Integer curr,
            Model model) {
        List<JieDTO> jieDTOS = jieService.selectJieByColumnIdAndStatusAndCurr(CustomizeJieTypeCode.JIE_NOTICE.getCode(),status,curr);
        Integer jieCount = jieService.countJieByColumnIdAndStatus(CustomizeJieTypeCode.JIE_NOTICE.getCode(),status);
        model.addAttribute("jieCount",jieCount);
        model.addAttribute("jieList",jieDTOS);
        return "jie/index";
    }

    @RequestMapping(value = "/jie/condition",method = RequestMethod.GET)
    public String selectConditionJie(Model model) {
        List<JieDTO> jieDTOS = jieService.selectJieByColumnIdAndCurr(CustomizeJieTypeCode.JIE_CONDITION.getCode(),1);
        Integer jieCount = jieService.countJieByColumnId(CustomizeJieTypeCode.JIE_NOTICE.getCode());
        model.addAttribute("jieCount",jieCount);
        model.addAttribute("jieList",jieDTOS);
        return "jie/index";
    }

    @RequestMapping(value = "/jie/condition/page/{curr}",method = RequestMethod.GET)
    public String selectConditionJie(@PathVariable(name = "curr") Integer curr, Model model) {
        List<JieDTO> jieDTOS = jieService.selectJieByColumnIdAndCurr(CustomizeJieTypeCode.JIE_CONDITION.getCode(),curr);
        Integer jieCount = jieService.countJieByColumnId(CustomizeJieTypeCode.JIE_NOTICE.getCode());
        model.addAttribute("jieCount",jieCount);
        model.addAttribute("jieList",jieDTOS);
        return "jie/index";
    }

    @RequestMapping(value = "/jie/condition/{status}",method = RequestMethod.GET)
    public String selectConditionJieWithStatus(@PathVariable(name = "status")String status, Model model) {
        List<JieDTO> jieDTOS = jieService.selectJieByColumnIdAndStatusAndCurr(CustomizeJieTypeCode.JIE_CONDITION.getCode(),status,1);
        Integer jieCount = jieService.countJieByColumnIdAndStatus(CustomizeJieTypeCode.JIE_NOTICE.getCode(),status);
        model.addAttribute("jieCount",jieCount);
        model.addAttribute("jieList",jieDTOS);
        return "jie/index";
    }

    @RequestMapping(value = "/jie/condition/{status}/page/{curr}",method = RequestMethod.GET)
    public String selectConditionJieWithStatus(
            @PathVariable(name = "status")String status,
            @PathVariable(name = "curr") Integer curr,
            Model model) {
        List<JieDTO> jieDTOS = jieService.selectJieByColumnIdAndStatusAndCurr(CustomizeJieTypeCode.JIE_CONDITION.getCode(),status,curr);
        Integer jieCount = jieService.countJieByColumnIdAndStatus(CustomizeJieTypeCode.JIE_NOTICE.getCode(),status);
        model.addAttribute("jieCount",jieCount);
        model.addAttribute("jieList",jieDTOS);
        return "jie/index";
    }

    @RequestMapping(value = "/jie",method = RequestMethod.POST)
    @ResponseBody
    public LayUIResult addJie(Jie jie, Authentication authentication) {
        LayUIResult layUIResult = jieService.insertOrUpdate(jie,authentication);
        return layUIResult;
    }

    @RequestMapping(value = "/jie/{jieId}",method = RequestMethod.GET)
    public String selectJieById(@PathVariable(name = "jieId") Integer jieId, Model model) {
        JieDTO jieDTO = jieService.selectJieById(jieId);
        List<ReplyDTO> replies = jieService.selectJieReply(jieId);
        model.addAttribute("jie",jieDTO);
        model.addAttribute("jieReplies",replies);
        return "jie/detail";
    }

    @RequestMapping(value = "/jie/{jieId}",method = RequestMethod.DELETE)
    @ResponseBody
    public LayUIResult deleteJieById(@PathVariable(name = "jieId") Integer jieId) {
        LayUIResult layUIResult = jieService.deleteJieById(jieId);
        return layUIResult;
    }

    @RequestMapping(value = "/jie",method = RequestMethod.PUT)
    @ResponseBody
    public LayUIResult updateJie(Jie jie) {
        LayUIResult layUIResult = jieService.updateJie(jie);
        return layUIResult;
    }

    @RequestMapping(value = "/jie/stick/{jieId}",method = RequestMethod.PUT)
    @ResponseBody
    public LayUIResult updateJieStickById(@PathVariable(name = "jieId") Integer jieId,Boolean rank) {
        LayUIResult layUIResult = jieService.updateJieStickById(jieId,rank);
        return layUIResult;
    }

    @RequestMapping(value = "/jie/boutique/{jieId}",method = RequestMethod.PUT)
    @ResponseBody
    public LayUIResult updateJieBoutiqueById(@PathVariable(name = "jieId") Integer jieId,Boolean rank) {
        LayUIResult layUIResult = jieService.updateJieBoutiqueById(jieId,rank);
        return layUIResult;
    }

    @RequestMapping(value = "/jie/all/{status}",method = RequestMethod.GET)
    public String selectJieByCurr(@PathVariable (name = "status") String status,Model model){
        List<JieDTO> jieDTOS = jieService.selectJieByStatusAndCurr(status,1);
        Integer jieCount = jieService.countJieByStatus(status);
        model.addAttribute("jieList",jieDTOS);
        model.addAttribute("jieCount",jieCount);
        return "jie/index";
    }

    @RequestMapping(value = "/jie/all/{status}/page/{curr}",method = RequestMethod.GET)
    public String selectJieByCurr(
            @PathVariable (name = "status") String status,
            @PathVariable (name = "curr") Integer curr,
            Model model){
        List<JieDTO> jieDTOS = jieService.selectJieByStatusAndCurr(status,curr);
        Integer jieCount = jieService.countJieByStatus(status);
        model.addAttribute("jieList",jieDTOS);
        model.addAttribute("jieCount",jieCount);
        return "jie/index";
    }

    @RequestMapping(value = "/jie/all",method = RequestMethod.GET)
    public String selectJieByCurr(Model model,String q){
//        List<JieDTO> jieDTOS = jieService.selectJieByCurr(1);
        List<JieDTO> jieDTOS = jieService.selectJieByCurrAndSearch(1,q);
        Integer jieCount = jieService.countJie();
        model.addAttribute("jieList",jieDTOS);
        model.addAttribute("jieCount",jieCount);
        return "jie/index";
    }

    @RequestMapping(value = "/jie/all/page/{curr}",method = RequestMethod.GET)
    public String selectJieByCurr(@PathVariable (name = "curr") Integer curr,Model model){
        List<JieDTO> jieDTOS = jieService.selectJieByCurr(curr);
        Integer jieCount = jieService.countJie();
        model.addAttribute("jieList",jieDTOS);
        model.addAttribute("jieCount",jieCount);
        return "jie/index";
    }

    @RequestMapping(value = "/jie/reply",method = RequestMethod.POST)
    @ResponseBody
    public LayUIResult reply(String content,Integer jid,Authentication authentication) {
        LayUIResult layUIResult = jieService.insertReply(jid,content,authentication);
        return layUIResult;
    }

    @RequestMapping(value = "/jie/reply/{id}",method = RequestMethod.DELETE)
    @ResponseBody
    public LayUIResult deleteReplyById(@PathVariable(name = "id") Integer id) {
        LayUIResult layUIResult = jieService.deleteReplyById(id);
        return layUIResult;
    }

    @RequestMapping(value = "/jie/reply",method = RequestMethod.PUT)
    @ResponseBody
    public LayUIResult updateReply(Reply reply){
        LayUIResult layUIResult = jieService.updateReply(reply);
        return layUIResult;
    }
}
