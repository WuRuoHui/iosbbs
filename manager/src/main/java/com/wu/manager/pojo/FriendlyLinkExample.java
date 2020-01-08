package com.wu.manager.pojo;

import java.util.ArrayList;
import java.util.List;

public class FriendlyLinkExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public FriendlyLinkExample() {
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

        public Criteria andLinkIdIsNull() {
            addCriterion("link_id is null");
            return (Criteria) this;
        }

        public Criteria andLinkIdIsNotNull() {
            addCriterion("link_id is not null");
            return (Criteria) this;
        }

        public Criteria andLinkIdEqualTo(Integer value) {
            addCriterion("link_id =", value, "linkId");
            return (Criteria) this;
        }

        public Criteria andLinkIdNotEqualTo(Integer value) {
            addCriterion("link_id <>", value, "linkId");
            return (Criteria) this;
        }

        public Criteria andLinkIdGreaterThan(Integer value) {
            addCriterion("link_id >", value, "linkId");
            return (Criteria) this;
        }

        public Criteria andLinkIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("link_id >=", value, "linkId");
            return (Criteria) this;
        }

        public Criteria andLinkIdLessThan(Integer value) {
            addCriterion("link_id <", value, "linkId");
            return (Criteria) this;
        }

        public Criteria andLinkIdLessThanOrEqualTo(Integer value) {
            addCriterion("link_id <=", value, "linkId");
            return (Criteria) this;
        }

        public Criteria andLinkIdIn(List<Integer> values) {
            addCriterion("link_id in", values, "linkId");
            return (Criteria) this;
        }

        public Criteria andLinkIdNotIn(List<Integer> values) {
            addCriterion("link_id not in", values, "linkId");
            return (Criteria) this;
        }

        public Criteria andLinkIdBetween(Integer value1, Integer value2) {
            addCriterion("link_id between", value1, value2, "linkId");
            return (Criteria) this;
        }

        public Criteria andLinkIdNotBetween(Integer value1, Integer value2) {
            addCriterion("link_id not between", value1, value2, "linkId");
            return (Criteria) this;
        }

        public Criteria andLogoIsNull() {
            addCriterion("logo is null");
            return (Criteria) this;
        }

        public Criteria andLogoIsNotNull() {
            addCriterion("logo is not null");
            return (Criteria) this;
        }

        public Criteria andLogoEqualTo(String value) {
            addCriterion("logo =", value, "logo");
            return (Criteria) this;
        }

        public Criteria andLogoNotEqualTo(String value) {
            addCriterion("logo <>", value, "logo");
            return (Criteria) this;
        }

        public Criteria andLogoGreaterThan(String value) {
            addCriterion("logo >", value, "logo");
            return (Criteria) this;
        }

        public Criteria andLogoGreaterThanOrEqualTo(String value) {
            addCriterion("logo >=", value, "logo");
            return (Criteria) this;
        }

        public Criteria andLogoLessThan(String value) {
            addCriterion("logo <", value, "logo");
            return (Criteria) this;
        }

        public Criteria andLogoLessThanOrEqualTo(String value) {
            addCriterion("logo <=", value, "logo");
            return (Criteria) this;
        }

        public Criteria andLogoLike(String value) {
            addCriterion("logo like", value, "logo");
            return (Criteria) this;
        }

        public Criteria andLogoNotLike(String value) {
            addCriterion("logo not like", value, "logo");
            return (Criteria) this;
        }

        public Criteria andLogoIn(List<String> values) {
            addCriterion("logo in", values, "logo");
            return (Criteria) this;
        }

        public Criteria andLogoNotIn(List<String> values) {
            addCriterion("logo not in", values, "logo");
            return (Criteria) this;
        }

        public Criteria andLogoBetween(String value1, String value2) {
            addCriterion("logo between", value1, value2, "logo");
            return (Criteria) this;
        }

        public Criteria andLogoNotBetween(String value1, String value2) {
            addCriterion("logo not between", value1, value2, "logo");
            return (Criteria) this;
        }

        public Criteria andWebsiteNameIsNull() {
            addCriterion("website_name is null");
            return (Criteria) this;
        }

        public Criteria andWebsiteNameIsNotNull() {
            addCriterion("website_name is not null");
            return (Criteria) this;
        }

        public Criteria andWebsiteNameEqualTo(String value) {
            addCriterion("website_name =", value, "websiteName");
            return (Criteria) this;
        }

        public Criteria andWebsiteNameNotEqualTo(String value) {
            addCriterion("website_name <>", value, "websiteName");
            return (Criteria) this;
        }

        public Criteria andWebsiteNameGreaterThan(String value) {
            addCriterion("website_name >", value, "websiteName");
            return (Criteria) this;
        }

        public Criteria andWebsiteNameGreaterThanOrEqualTo(String value) {
            addCriterion("website_name >=", value, "websiteName");
            return (Criteria) this;
        }

        public Criteria andWebsiteNameLessThan(String value) {
            addCriterion("website_name <", value, "websiteName");
            return (Criteria) this;
        }

        public Criteria andWebsiteNameLessThanOrEqualTo(String value) {
            addCriterion("website_name <=", value, "websiteName");
            return (Criteria) this;
        }

        public Criteria andWebsiteNameLike(String value) {
            addCriterion("website_name like", value, "websiteName");
            return (Criteria) this;
        }

        public Criteria andWebsiteNameNotLike(String value) {
            addCriterion("website_name not like", value, "websiteName");
            return (Criteria) this;
        }

        public Criteria andWebsiteNameIn(List<String> values) {
            addCriterion("website_name in", values, "websiteName");
            return (Criteria) this;
        }

        public Criteria andWebsiteNameNotIn(List<String> values) {
            addCriterion("website_name not in", values, "websiteName");
            return (Criteria) this;
        }

        public Criteria andWebsiteNameBetween(String value1, String value2) {
            addCriterion("website_name between", value1, value2, "websiteName");
            return (Criteria) this;
        }

        public Criteria andWebsiteNameNotBetween(String value1, String value2) {
            addCriterion("website_name not between", value1, value2, "websiteName");
            return (Criteria) this;
        }

        public Criteria andWebsiteUrlIsNull() {
            addCriterion("website_url is null");
            return (Criteria) this;
        }

        public Criteria andWebsiteUrlIsNotNull() {
            addCriterion("website_url is not null");
            return (Criteria) this;
        }

        public Criteria andWebsiteUrlEqualTo(String value) {
            addCriterion("website_url =", value, "websiteUrl");
            return (Criteria) this;
        }

        public Criteria andWebsiteUrlNotEqualTo(String value) {
            addCriterion("website_url <>", value, "websiteUrl");
            return (Criteria) this;
        }

        public Criteria andWebsiteUrlGreaterThan(String value) {
            addCriterion("website_url >", value, "websiteUrl");
            return (Criteria) this;
        }

        public Criteria andWebsiteUrlGreaterThanOrEqualTo(String value) {
            addCriterion("website_url >=", value, "websiteUrl");
            return (Criteria) this;
        }

        public Criteria andWebsiteUrlLessThan(String value) {
            addCriterion("website_url <", value, "websiteUrl");
            return (Criteria) this;
        }

        public Criteria andWebsiteUrlLessThanOrEqualTo(String value) {
            addCriterion("website_url <=", value, "websiteUrl");
            return (Criteria) this;
        }

        public Criteria andWebsiteUrlLike(String value) {
            addCriterion("website_url like", value, "websiteUrl");
            return (Criteria) this;
        }

        public Criteria andWebsiteUrlNotLike(String value) {
            addCriterion("website_url not like", value, "websiteUrl");
            return (Criteria) this;
        }

        public Criteria andWebsiteUrlIn(List<String> values) {
            addCriterion("website_url in", values, "websiteUrl");
            return (Criteria) this;
        }

        public Criteria andWebsiteUrlNotIn(List<String> values) {
            addCriterion("website_url not in", values, "websiteUrl");
            return (Criteria) this;
        }

        public Criteria andWebsiteUrlBetween(String value1, String value2) {
            addCriterion("website_url between", value1, value2, "websiteUrl");
            return (Criteria) this;
        }

        public Criteria andWebsiteUrlNotBetween(String value1, String value2) {
            addCriterion("website_url not between", value1, value2, "websiteUrl");
            return (Criteria) this;
        }

        public Criteria andMasterEmailIsNull() {
            addCriterion("master_email is null");
            return (Criteria) this;
        }

        public Criteria andMasterEmailIsNotNull() {
            addCriterion("master_email is not null");
            return (Criteria) this;
        }

        public Criteria andMasterEmailEqualTo(String value) {
            addCriterion("master_email =", value, "masterEmail");
            return (Criteria) this;
        }

        public Criteria andMasterEmailNotEqualTo(String value) {
            addCriterion("master_email <>", value, "masterEmail");
            return (Criteria) this;
        }

        public Criteria andMasterEmailGreaterThan(String value) {
            addCriterion("master_email >", value, "masterEmail");
            return (Criteria) this;
        }

        public Criteria andMasterEmailGreaterThanOrEqualTo(String value) {
            addCriterion("master_email >=", value, "masterEmail");
            return (Criteria) this;
        }

        public Criteria andMasterEmailLessThan(String value) {
            addCriterion("master_email <", value, "masterEmail");
            return (Criteria) this;
        }

        public Criteria andMasterEmailLessThanOrEqualTo(String value) {
            addCriterion("master_email <=", value, "masterEmail");
            return (Criteria) this;
        }

        public Criteria andMasterEmailLike(String value) {
            addCriterion("master_email like", value, "masterEmail");
            return (Criteria) this;
        }

        public Criteria andMasterEmailNotLike(String value) {
            addCriterion("master_email not like", value, "masterEmail");
            return (Criteria) this;
        }

        public Criteria andMasterEmailIn(List<String> values) {
            addCriterion("master_email in", values, "masterEmail");
            return (Criteria) this;
        }

        public Criteria andMasterEmailNotIn(List<String> values) {
            addCriterion("master_email not in", values, "masterEmail");
            return (Criteria) this;
        }

        public Criteria andMasterEmailBetween(String value1, String value2) {
            addCriterion("master_email between", value1, value2, "masterEmail");
            return (Criteria) this;
        }

        public Criteria andMasterEmailNotBetween(String value1, String value2) {
            addCriterion("master_email not between", value1, value2, "masterEmail");
            return (Criteria) this;
        }

        public Criteria andAddTimeIsNull() {
            addCriterion("add_time is null");
            return (Criteria) this;
        }

        public Criteria andAddTimeIsNotNull() {
            addCriterion("add_time is not null");
            return (Criteria) this;
        }

        public Criteria andAddTimeEqualTo(Long value) {
            addCriterion("add_time =", value, "addTime");
            return (Criteria) this;
        }

        public Criteria andAddTimeNotEqualTo(Long value) {
            addCriterion("add_time <>", value, "addTime");
            return (Criteria) this;
        }

        public Criteria andAddTimeGreaterThan(Long value) {
            addCriterion("add_time >", value, "addTime");
            return (Criteria) this;
        }

        public Criteria andAddTimeGreaterThanOrEqualTo(Long value) {
            addCriterion("add_time >=", value, "addTime");
            return (Criteria) this;
        }

        public Criteria andAddTimeLessThan(Long value) {
            addCriterion("add_time <", value, "addTime");
            return (Criteria) this;
        }

        public Criteria andAddTimeLessThanOrEqualTo(Long value) {
            addCriterion("add_time <=", value, "addTime");
            return (Criteria) this;
        }

        public Criteria andAddTimeIn(List<Long> values) {
            addCriterion("add_time in", values, "addTime");
            return (Criteria) this;
        }

        public Criteria andAddTimeNotIn(List<Long> values) {
            addCriterion("add_time not in", values, "addTime");
            return (Criteria) this;
        }

        public Criteria andAddTimeBetween(Long value1, Long value2) {
            addCriterion("add_time between", value1, value2, "addTime");
            return (Criteria) this;
        }

        public Criteria andAddTimeNotBetween(Long value1, Long value2) {
            addCriterion("add_time not between", value1, value2, "addTime");
            return (Criteria) this;
        }

        public Criteria andModifyTimeIsNull() {
            addCriterion("modify_time is null");
            return (Criteria) this;
        }

        public Criteria andModifyTimeIsNotNull() {
            addCriterion("modify_time is not null");
            return (Criteria) this;
        }

        public Criteria andModifyTimeEqualTo(Long value) {
            addCriterion("modify_time =", value, "modifyTime");
            return (Criteria) this;
        }

        public Criteria andModifyTimeNotEqualTo(Long value) {
            addCriterion("modify_time <>", value, "modifyTime");
            return (Criteria) this;
        }

        public Criteria andModifyTimeGreaterThan(Long value) {
            addCriterion("modify_time >", value, "modifyTime");
            return (Criteria) this;
        }

        public Criteria andModifyTimeGreaterThanOrEqualTo(Long value) {
            addCriterion("modify_time >=", value, "modifyTime");
            return (Criteria) this;
        }

        public Criteria andModifyTimeLessThan(Long value) {
            addCriterion("modify_time <", value, "modifyTime");
            return (Criteria) this;
        }

        public Criteria andModifyTimeLessThanOrEqualTo(Long value) {
            addCriterion("modify_time <=", value, "modifyTime");
            return (Criteria) this;
        }

        public Criteria andModifyTimeIn(List<Long> values) {
            addCriterion("modify_time in", values, "modifyTime");
            return (Criteria) this;
        }

        public Criteria andModifyTimeNotIn(List<Long> values) {
            addCriterion("modify_time not in", values, "modifyTime");
            return (Criteria) this;
        }

        public Criteria andModifyTimeBetween(Long value1, Long value2) {
            addCriterion("modify_time between", value1, value2, "modifyTime");
            return (Criteria) this;
        }

        public Criteria andModifyTimeNotBetween(Long value1, Long value2) {
            addCriterion("modify_time not between", value1, value2, "modifyTime");
            return (Criteria) this;
        }

        public Criteria andShowAddressIsNull() {
            addCriterion("show_address is null");
            return (Criteria) this;
        }

        public Criteria andShowAddressIsNotNull() {
            addCriterion("show_address is not null");
            return (Criteria) this;
        }

        public Criteria andShowAddressEqualTo(String value) {
            addCriterion("show_address =", value, "showAddress");
            return (Criteria) this;
        }

        public Criteria andShowAddressNotEqualTo(String value) {
            addCriterion("show_address <>", value, "showAddress");
            return (Criteria) this;
        }

        public Criteria andShowAddressGreaterThan(String value) {
            addCriterion("show_address >", value, "showAddress");
            return (Criteria) this;
        }

        public Criteria andShowAddressGreaterThanOrEqualTo(String value) {
            addCriterion("show_address >=", value, "showAddress");
            return (Criteria) this;
        }

        public Criteria andShowAddressLessThan(String value) {
            addCriterion("show_address <", value, "showAddress");
            return (Criteria) this;
        }

        public Criteria andShowAddressLessThanOrEqualTo(String value) {
            addCriterion("show_address <=", value, "showAddress");
            return (Criteria) this;
        }

        public Criteria andShowAddressLike(String value) {
            addCriterion("show_address like", value, "showAddress");
            return (Criteria) this;
        }

        public Criteria andShowAddressNotLike(String value) {
            addCriterion("show_address not like", value, "showAddress");
            return (Criteria) this;
        }

        public Criteria andShowAddressIn(List<String> values) {
            addCriterion("show_address in", values, "showAddress");
            return (Criteria) this;
        }

        public Criteria andShowAddressNotIn(List<String> values) {
            addCriterion("show_address not in", values, "showAddress");
            return (Criteria) this;
        }

        public Criteria andShowAddressBetween(String value1, String value2) {
            addCriterion("show_address between", value1, value2, "showAddress");
            return (Criteria) this;
        }

        public Criteria andShowAddressNotBetween(String value1, String value2) {
            addCriterion("show_address not between", value1, value2, "showAddress");
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