package com.wu.manager.pojo;

import java.util.ArrayList;
import java.util.List;

public class SystemParameterExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public SystemParameterExample() {
        oredCriteria = new ArrayList<>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Integer value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Integer value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Integer value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Integer value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Integer value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Integer> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Integer> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Integer value1, Integer value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Integer value1, Integer value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andCmsNameIsNull() {
            addCriterion("cms_name is null");
            return (Criteria) this;
        }

        public Criteria andCmsNameIsNotNull() {
            addCriterion("cms_name is not null");
            return (Criteria) this;
        }

        public Criteria andCmsNameEqualTo(String value) {
            addCriterion("cms_name =", value, "cmsName");
            return (Criteria) this;
        }

        public Criteria andCmsNameNotEqualTo(String value) {
            addCriterion("cms_name <>", value, "cmsName");
            return (Criteria) this;
        }

        public Criteria andCmsNameGreaterThan(String value) {
            addCriterion("cms_name >", value, "cmsName");
            return (Criteria) this;
        }

        public Criteria andCmsNameGreaterThanOrEqualTo(String value) {
            addCriterion("cms_name >=", value, "cmsName");
            return (Criteria) this;
        }

        public Criteria andCmsNameLessThan(String value) {
            addCriterion("cms_name <", value, "cmsName");
            return (Criteria) this;
        }

        public Criteria andCmsNameLessThanOrEqualTo(String value) {
            addCriterion("cms_name <=", value, "cmsName");
            return (Criteria) this;
        }

        public Criteria andCmsNameLike(String value) {
            addCriterion("cms_name like", value, "cmsName");
            return (Criteria) this;
        }

        public Criteria andCmsNameNotLike(String value) {
            addCriterion("cms_name not like", value, "cmsName");
            return (Criteria) this;
        }

        public Criteria andCmsNameIn(List<String> values) {
            addCriterion("cms_name in", values, "cmsName");
            return (Criteria) this;
        }

        public Criteria andCmsNameNotIn(List<String> values) {
            addCriterion("cms_name not in", values, "cmsName");
            return (Criteria) this;
        }

        public Criteria andCmsNameBetween(String value1, String value2) {
            addCriterion("cms_name between", value1, value2, "cmsName");
            return (Criteria) this;
        }

        public Criteria andCmsNameNotBetween(String value1, String value2) {
            addCriterion("cms_name not between", value1, value2, "cmsName");
            return (Criteria) this;
        }

        public Criteria andVersionIsNull() {
            addCriterion("version is null");
            return (Criteria) this;
        }

        public Criteria andVersionIsNotNull() {
            addCriterion("version is not null");
            return (Criteria) this;
        }

        public Criteria andVersionEqualTo(String value) {
            addCriterion("version =", value, "version");
            return (Criteria) this;
        }

        public Criteria andVersionNotEqualTo(String value) {
            addCriterion("version <>", value, "version");
            return (Criteria) this;
        }

        public Criteria andVersionGreaterThan(String value) {
            addCriterion("version >", value, "version");
            return (Criteria) this;
        }

        public Criteria andVersionGreaterThanOrEqualTo(String value) {
            addCriterion("version >=", value, "version");
            return (Criteria) this;
        }

        public Criteria andVersionLessThan(String value) {
            addCriterion("version <", value, "version");
            return (Criteria) this;
        }

        public Criteria andVersionLessThanOrEqualTo(String value) {
            addCriterion("version <=", value, "version");
            return (Criteria) this;
        }

        public Criteria andVersionLike(String value) {
            addCriterion("version like", value, "version");
            return (Criteria) this;
        }

        public Criteria andVersionNotLike(String value) {
            addCriterion("version not like", value, "version");
            return (Criteria) this;
        }

        public Criteria andVersionIn(List<String> values) {
            addCriterion("version in", values, "version");
            return (Criteria) this;
        }

        public Criteria andVersionNotIn(List<String> values) {
            addCriterion("version not in", values, "version");
            return (Criteria) this;
        }

        public Criteria andVersionBetween(String value1, String value2) {
            addCriterion("version between", value1, value2, "version");
            return (Criteria) this;
        }

        public Criteria andVersionNotBetween(String value1, String value2) {
            addCriterion("version not between", value1, value2, "version");
            return (Criteria) this;
        }

        public Criteria andAuthorIsNull() {
            addCriterion("author is null");
            return (Criteria) this;
        }

        public Criteria andAuthorIsNotNull() {
            addCriterion("author is not null");
            return (Criteria) this;
        }

        public Criteria andAuthorEqualTo(String value) {
            addCriterion("author =", value, "author");
            return (Criteria) this;
        }

        public Criteria andAuthorNotEqualTo(String value) {
            addCriterion("author <>", value, "author");
            return (Criteria) this;
        }

        public Criteria andAuthorGreaterThan(String value) {
            addCriterion("author >", value, "author");
            return (Criteria) this;
        }

        public Criteria andAuthorGreaterThanOrEqualTo(String value) {
            addCriterion("author >=", value, "author");
            return (Criteria) this;
        }

        public Criteria andAuthorLessThan(String value) {
            addCriterion("author <", value, "author");
            return (Criteria) this;
        }

        public Criteria andAuthorLessThanOrEqualTo(String value) {
            addCriterion("author <=", value, "author");
            return (Criteria) this;
        }

        public Criteria andAuthorLike(String value) {
            addCriterion("author like", value, "author");
            return (Criteria) this;
        }

        public Criteria andAuthorNotLike(String value) {
            addCriterion("author not like", value, "author");
            return (Criteria) this;
        }

        public Criteria andAuthorIn(List<String> values) {
            addCriterion("author in", values, "author");
            return (Criteria) this;
        }

        public Criteria andAuthorNotIn(List<String> values) {
            addCriterion("author not in", values, "author");
            return (Criteria) this;
        }

        public Criteria andAuthorBetween(String value1, String value2) {
            addCriterion("author between", value1, value2, "author");
            return (Criteria) this;
        }

        public Criteria andAuthorNotBetween(String value1, String value2) {
            addCriterion("author not between", value1, value2, "author");
            return (Criteria) this;
        }

        public Criteria andHomePageIsNull() {
            addCriterion("home_page is null");
            return (Criteria) this;
        }

        public Criteria andHomePageIsNotNull() {
            addCriterion("home_page is not null");
            return (Criteria) this;
        }

        public Criteria andHomePageEqualTo(String value) {
            addCriterion("home_page =", value, "homePage");
            return (Criteria) this;
        }

        public Criteria andHomePageNotEqualTo(String value) {
            addCriterion("home_page <>", value, "homePage");
            return (Criteria) this;
        }

        public Criteria andHomePageGreaterThan(String value) {
            addCriterion("home_page >", value, "homePage");
            return (Criteria) this;
        }

        public Criteria andHomePageGreaterThanOrEqualTo(String value) {
            addCriterion("home_page >=", value, "homePage");
            return (Criteria) this;
        }

        public Criteria andHomePageLessThan(String value) {
            addCriterion("home_page <", value, "homePage");
            return (Criteria) this;
        }

        public Criteria andHomePageLessThanOrEqualTo(String value) {
            addCriterion("home_page <=", value, "homePage");
            return (Criteria) this;
        }

        public Criteria andHomePageLike(String value) {
            addCriterion("home_page like", value, "homePage");
            return (Criteria) this;
        }

        public Criteria andHomePageNotLike(String value) {
            addCriterion("home_page not like", value, "homePage");
            return (Criteria) this;
        }

        public Criteria andHomePageIn(List<String> values) {
            addCriterion("home_page in", values, "homePage");
            return (Criteria) this;
        }

        public Criteria andHomePageNotIn(List<String> values) {
            addCriterion("home_page not in", values, "homePage");
            return (Criteria) this;
        }

        public Criteria andHomePageBetween(String value1, String value2) {
            addCriterion("home_page between", value1, value2, "homePage");
            return (Criteria) this;
        }

        public Criteria andHomePageNotBetween(String value1, String value2) {
            addCriterion("home_page not between", value1, value2, "homePage");
            return (Criteria) this;
        }

        public Criteria andServerIsNull() {
            addCriterion("server is null");
            return (Criteria) this;
        }

        public Criteria andServerIsNotNull() {
            addCriterion("server is not null");
            return (Criteria) this;
        }

        public Criteria andServerEqualTo(String value) {
            addCriterion("server =", value, "server");
            return (Criteria) this;
        }

        public Criteria andServerNotEqualTo(String value) {
            addCriterion("server <>", value, "server");
            return (Criteria) this;
        }

        public Criteria andServerGreaterThan(String value) {
            addCriterion("server >", value, "server");
            return (Criteria) this;
        }

        public Criteria andServerGreaterThanOrEqualTo(String value) {
            addCriterion("server >=", value, "server");
            return (Criteria) this;
        }

        public Criteria andServerLessThan(String value) {
            addCriterion("server <", value, "server");
            return (Criteria) this;
        }

        public Criteria andServerLessThanOrEqualTo(String value) {
            addCriterion("server <=", value, "server");
            return (Criteria) this;
        }

        public Criteria andServerLike(String value) {
            addCriterion("server like", value, "server");
            return (Criteria) this;
        }

        public Criteria andServerNotLike(String value) {
            addCriterion("server not like", value, "server");
            return (Criteria) this;
        }

        public Criteria andServerIn(List<String> values) {
            addCriterion("server in", values, "server");
            return (Criteria) this;
        }

        public Criteria andServerNotIn(List<String> values) {
            addCriterion("server not in", values, "server");
            return (Criteria) this;
        }

        public Criteria andServerBetween(String value1, String value2) {
            addCriterion("server between", value1, value2, "server");
            return (Criteria) this;
        }

        public Criteria andServerNotBetween(String value1, String value2) {
            addCriterion("server not between", value1, value2, "server");
            return (Criteria) this;
        }

        public Criteria andDataBaseIsNull() {
            addCriterion("data_base is null");
            return (Criteria) this;
        }

        public Criteria andDataBaseIsNotNull() {
            addCriterion("data_base is not null");
            return (Criteria) this;
        }

        public Criteria andDataBaseEqualTo(String value) {
            addCriterion("data_base =", value, "dataBase");
            return (Criteria) this;
        }

        public Criteria andDataBaseNotEqualTo(String value) {
            addCriterion("data_base <>", value, "dataBase");
            return (Criteria) this;
        }

        public Criteria andDataBaseGreaterThan(String value) {
            addCriterion("data_base >", value, "dataBase");
            return (Criteria) this;
        }

        public Criteria andDataBaseGreaterThanOrEqualTo(String value) {
            addCriterion("data_base >=", value, "dataBase");
            return (Criteria) this;
        }

        public Criteria andDataBaseLessThan(String value) {
            addCriterion("data_base <", value, "dataBase");
            return (Criteria) this;
        }

        public Criteria andDataBaseLessThanOrEqualTo(String value) {
            addCriterion("data_base <=", value, "dataBase");
            return (Criteria) this;
        }

        public Criteria andDataBaseLike(String value) {
            addCriterion("data_base like", value, "dataBase");
            return (Criteria) this;
        }

        public Criteria andDataBaseNotLike(String value) {
            addCriterion("data_base not like", value, "dataBase");
            return (Criteria) this;
        }

        public Criteria andDataBaseIn(List<String> values) {
            addCriterion("data_base in", values, "dataBase");
            return (Criteria) this;
        }

        public Criteria andDataBaseNotIn(List<String> values) {
            addCriterion("data_base not in", values, "dataBase");
            return (Criteria) this;
        }

        public Criteria andDataBaseBetween(String value1, String value2) {
            addCriterion("data_base between", value1, value2, "dataBase");
            return (Criteria) this;
        }

        public Criteria andDataBaseNotBetween(String value1, String value2) {
            addCriterion("data_base not between", value1, value2, "dataBase");
            return (Criteria) this;
        }

        public Criteria andMaxUploadIsNull() {
            addCriterion("max_upload is null");
            return (Criteria) this;
        }

        public Criteria andMaxUploadIsNotNull() {
            addCriterion("max_upload is not null");
            return (Criteria) this;
        }

        public Criteria andMaxUploadEqualTo(String value) {
            addCriterion("max_upload =", value, "maxUpload");
            return (Criteria) this;
        }

        public Criteria andMaxUploadNotEqualTo(String value) {
            addCriterion("max_upload <>", value, "maxUpload");
            return (Criteria) this;
        }

        public Criteria andMaxUploadGreaterThan(String value) {
            addCriterion("max_upload >", value, "maxUpload");
            return (Criteria) this;
        }

        public Criteria andMaxUploadGreaterThanOrEqualTo(String value) {
            addCriterion("max_upload >=", value, "maxUpload");
            return (Criteria) this;
        }

        public Criteria andMaxUploadLessThan(String value) {
            addCriterion("max_upload <", value, "maxUpload");
            return (Criteria) this;
        }

        public Criteria andMaxUploadLessThanOrEqualTo(String value) {
            addCriterion("max_upload <=", value, "maxUpload");
            return (Criteria) this;
        }

        public Criteria andMaxUploadLike(String value) {
            addCriterion("max_upload like", value, "maxUpload");
            return (Criteria) this;
        }

        public Criteria andMaxUploadNotLike(String value) {
            addCriterion("max_upload not like", value, "maxUpload");
            return (Criteria) this;
        }

        public Criteria andMaxUploadIn(List<String> values) {
            addCriterion("max_upload in", values, "maxUpload");
            return (Criteria) this;
        }

        public Criteria andMaxUploadNotIn(List<String> values) {
            addCriterion("max_upload not in", values, "maxUpload");
            return (Criteria) this;
        }

        public Criteria andMaxUploadBetween(String value1, String value2) {
            addCriterion("max_upload between", value1, value2, "maxUpload");
            return (Criteria) this;
        }

        public Criteria andMaxUploadNotBetween(String value1, String value2) {
            addCriterion("max_upload not between", value1, value2, "maxUpload");
            return (Criteria) this;
        }

        public Criteria andUserRightsIsNull() {
            addCriterion("user_rights is null");
            return (Criteria) this;
        }

        public Criteria andUserRightsIsNotNull() {
            addCriterion("user_rights is not null");
            return (Criteria) this;
        }

        public Criteria andUserRightsEqualTo(String value) {
            addCriterion("user_rights =", value, "userRights");
            return (Criteria) this;
        }

        public Criteria andUserRightsNotEqualTo(String value) {
            addCriterion("user_rights <>", value, "userRights");
            return (Criteria) this;
        }

        public Criteria andUserRightsGreaterThan(String value) {
            addCriterion("user_rights >", value, "userRights");
            return (Criteria) this;
        }

        public Criteria andUserRightsGreaterThanOrEqualTo(String value) {
            addCriterion("user_rights >=", value, "userRights");
            return (Criteria) this;
        }

        public Criteria andUserRightsLessThan(String value) {
            addCriterion("user_rights <", value, "userRights");
            return (Criteria) this;
        }

        public Criteria andUserRightsLessThanOrEqualTo(String value) {
            addCriterion("user_rights <=", value, "userRights");
            return (Criteria) this;
        }

        public Criteria andUserRightsLike(String value) {
            addCriterion("user_rights like", value, "userRights");
            return (Criteria) this;
        }

        public Criteria andUserRightsNotLike(String value) {
            addCriterion("user_rights not like", value, "userRights");
            return (Criteria) this;
        }

        public Criteria andUserRightsIn(List<String> values) {
            addCriterion("user_rights in", values, "userRights");
            return (Criteria) this;
        }

        public Criteria andUserRightsNotIn(List<String> values) {
            addCriterion("user_rights not in", values, "userRights");
            return (Criteria) this;
        }

        public Criteria andUserRightsBetween(String value1, String value2) {
            addCriterion("user_rights between", value1, value2, "userRights");
            return (Criteria) this;
        }

        public Criteria andUserRightsNotBetween(String value1, String value2) {
            addCriterion("user_rights not between", value1, value2, "userRights");
            return (Criteria) this;
        }

        public Criteria andKeywordsIsNull() {
            addCriterion("keywords is null");
            return (Criteria) this;
        }

        public Criteria andKeywordsIsNotNull() {
            addCriterion("keywords is not null");
            return (Criteria) this;
        }

        public Criteria andKeywordsEqualTo(String value) {
            addCriterion("keywords =", value, "keywords");
            return (Criteria) this;
        }

        public Criteria andKeywordsNotEqualTo(String value) {
            addCriterion("keywords <>", value, "keywords");
            return (Criteria) this;
        }

        public Criteria andKeywordsGreaterThan(String value) {
            addCriterion("keywords >", value, "keywords");
            return (Criteria) this;
        }

        public Criteria andKeywordsGreaterThanOrEqualTo(String value) {
            addCriterion("keywords >=", value, "keywords");
            return (Criteria) this;
        }

        public Criteria andKeywordsLessThan(String value) {
            addCriterion("keywords <", value, "keywords");
            return (Criteria) this;
        }

        public Criteria andKeywordsLessThanOrEqualTo(String value) {
            addCriterion("keywords <=", value, "keywords");
            return (Criteria) this;
        }

        public Criteria andKeywordsLike(String value) {
            addCriterion("keywords like", value, "keywords");
            return (Criteria) this;
        }

        public Criteria andKeywordsNotLike(String value) {
            addCriterion("keywords not like", value, "keywords");
            return (Criteria) this;
        }

        public Criteria andKeywordsIn(List<String> values) {
            addCriterion("keywords in", values, "keywords");
            return (Criteria) this;
        }

        public Criteria andKeywordsNotIn(List<String> values) {
            addCriterion("keywords not in", values, "keywords");
            return (Criteria) this;
        }

        public Criteria andKeywordsBetween(String value1, String value2) {
            addCriterion("keywords between", value1, value2, "keywords");
            return (Criteria) this;
        }

        public Criteria andKeywordsNotBetween(String value1, String value2) {
            addCriterion("keywords not between", value1, value2, "keywords");
            return (Criteria) this;
        }

        public Criteria andPowerbyIsNull() {
            addCriterion("powerby is null");
            return (Criteria) this;
        }

        public Criteria andPowerbyIsNotNull() {
            addCriterion("powerby is not null");
            return (Criteria) this;
        }

        public Criteria andPowerbyEqualTo(String value) {
            addCriterion("powerby =", value, "powerby");
            return (Criteria) this;
        }

        public Criteria andPowerbyNotEqualTo(String value) {
            addCriterion("powerby <>", value, "powerby");
            return (Criteria) this;
        }

        public Criteria andPowerbyGreaterThan(String value) {
            addCriterion("powerby >", value, "powerby");
            return (Criteria) this;
        }

        public Criteria andPowerbyGreaterThanOrEqualTo(String value) {
            addCriterion("powerby >=", value, "powerby");
            return (Criteria) this;
        }

        public Criteria andPowerbyLessThan(String value) {
            addCriterion("powerby <", value, "powerby");
            return (Criteria) this;
        }

        public Criteria andPowerbyLessThanOrEqualTo(String value) {
            addCriterion("powerby <=", value, "powerby");
            return (Criteria) this;
        }

        public Criteria andPowerbyLike(String value) {
            addCriterion("powerby like", value, "powerby");
            return (Criteria) this;
        }

        public Criteria andPowerbyNotLike(String value) {
            addCriterion("powerby not like", value, "powerby");
            return (Criteria) this;
        }

        public Criteria andPowerbyIn(List<String> values) {
            addCriterion("powerby in", values, "powerby");
            return (Criteria) this;
        }

        public Criteria andPowerbyNotIn(List<String> values) {
            addCriterion("powerby not in", values, "powerby");
            return (Criteria) this;
        }

        public Criteria andPowerbyBetween(String value1, String value2) {
            addCriterion("powerby between", value1, value2, "powerby");
            return (Criteria) this;
        }

        public Criteria andPowerbyNotBetween(String value1, String value2) {
            addCriterion("powerby not between", value1, value2, "powerby");
            return (Criteria) this;
        }

        public Criteria andDescriptionIsNull() {
            addCriterion("description is null");
            return (Criteria) this;
        }

        public Criteria andDescriptionIsNotNull() {
            addCriterion("description is not null");
            return (Criteria) this;
        }

        public Criteria andDescriptionEqualTo(String value) {
            addCriterion("description =", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionNotEqualTo(String value) {
            addCriterion("description <>", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionGreaterThan(String value) {
            addCriterion("description >", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionGreaterThanOrEqualTo(String value) {
            addCriterion("description >=", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionLessThan(String value) {
            addCriterion("description <", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionLessThanOrEqualTo(String value) {
            addCriterion("description <=", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionLike(String value) {
            addCriterion("description like", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionNotLike(String value) {
            addCriterion("description not like", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionIn(List<String> values) {
            addCriterion("description in", values, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionNotIn(List<String> values) {
            addCriterion("description not in", values, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionBetween(String value1, String value2) {
            addCriterion("description between", value1, value2, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionNotBetween(String value1, String value2) {
            addCriterion("description not between", value1, value2, "description");
            return (Criteria) this;
        }

        public Criteria andRecordIsNull() {
            addCriterion("record is null");
            return (Criteria) this;
        }

        public Criteria andRecordIsNotNull() {
            addCriterion("record is not null");
            return (Criteria) this;
        }

        public Criteria andRecordEqualTo(String value) {
            addCriterion("record =", value, "record");
            return (Criteria) this;
        }

        public Criteria andRecordNotEqualTo(String value) {
            addCriterion("record <>", value, "record");
            return (Criteria) this;
        }

        public Criteria andRecordGreaterThan(String value) {
            addCriterion("record >", value, "record");
            return (Criteria) this;
        }

        public Criteria andRecordGreaterThanOrEqualTo(String value) {
            addCriterion("record >=", value, "record");
            return (Criteria) this;
        }

        public Criteria andRecordLessThan(String value) {
            addCriterion("record <", value, "record");
            return (Criteria) this;
        }

        public Criteria andRecordLessThanOrEqualTo(String value) {
            addCriterion("record <=", value, "record");
            return (Criteria) this;
        }

        public Criteria andRecordLike(String value) {
            addCriterion("record like", value, "record");
            return (Criteria) this;
        }

        public Criteria andRecordNotLike(String value) {
            addCriterion("record not like", value, "record");
            return (Criteria) this;
        }

        public Criteria andRecordIn(List<String> values) {
            addCriterion("record in", values, "record");
            return (Criteria) this;
        }

        public Criteria andRecordNotIn(List<String> values) {
            addCriterion("record not in", values, "record");
            return (Criteria) this;
        }

        public Criteria andRecordBetween(String value1, String value2) {
            addCriterion("record between", value1, value2, "record");
            return (Criteria) this;
        }

        public Criteria andRecordNotBetween(String value1, String value2) {
            addCriterion("record not between", value1, value2, "record");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {
        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}