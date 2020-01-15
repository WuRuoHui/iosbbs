package com.wu.manager.pojo;

import java.util.ArrayList;
import java.util.List;

public class UserGradeExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public UserGradeExample() {
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

        public Criteria andGradeIconIsNull() {
            addCriterion("grade_icon is null");
            return (Criteria) this;
        }

        public Criteria andGradeIconIsNotNull() {
            addCriterion("grade_icon is not null");
            return (Criteria) this;
        }

        public Criteria andGradeIconEqualTo(String value) {
            addCriterion("grade_icon =", value, "gradeIcon");
            return (Criteria) this;
        }

        public Criteria andGradeIconNotEqualTo(String value) {
            addCriterion("grade_icon <>", value, "gradeIcon");
            return (Criteria) this;
        }

        public Criteria andGradeIconGreaterThan(String value) {
            addCriterion("grade_icon >", value, "gradeIcon");
            return (Criteria) this;
        }

        public Criteria andGradeIconGreaterThanOrEqualTo(String value) {
            addCriterion("grade_icon >=", value, "gradeIcon");
            return (Criteria) this;
        }

        public Criteria andGradeIconLessThan(String value) {
            addCriterion("grade_icon <", value, "gradeIcon");
            return (Criteria) this;
        }

        public Criteria andGradeIconLessThanOrEqualTo(String value) {
            addCriterion("grade_icon <=", value, "gradeIcon");
            return (Criteria) this;
        }

        public Criteria andGradeIconLike(String value) {
            addCriterion("grade_icon like", value, "gradeIcon");
            return (Criteria) this;
        }

        public Criteria andGradeIconNotLike(String value) {
            addCriterion("grade_icon not like", value, "gradeIcon");
            return (Criteria) this;
        }

        public Criteria andGradeIconIn(List<String> values) {
            addCriterion("grade_icon in", values, "gradeIcon");
            return (Criteria) this;
        }

        public Criteria andGradeIconNotIn(List<String> values) {
            addCriterion("grade_icon not in", values, "gradeIcon");
            return (Criteria) this;
        }

        public Criteria andGradeIconBetween(String value1, String value2) {
            addCriterion("grade_icon between", value1, value2, "gradeIcon");
            return (Criteria) this;
        }

        public Criteria andGradeIconNotBetween(String value1, String value2) {
            addCriterion("grade_icon not between", value1, value2, "gradeIcon");
            return (Criteria) this;
        }

        public Criteria andGradeNameIsNull() {
            addCriterion("grade_name is null");
            return (Criteria) this;
        }

        public Criteria andGradeNameIsNotNull() {
            addCriterion("grade_name is not null");
            return (Criteria) this;
        }

        public Criteria andGradeNameEqualTo(String value) {
            addCriterion("grade_name =", value, "gradeName");
            return (Criteria) this;
        }

        public Criteria andGradeNameNotEqualTo(String value) {
            addCriterion("grade_name <>", value, "gradeName");
            return (Criteria) this;
        }

        public Criteria andGradeNameGreaterThan(String value) {
            addCriterion("grade_name >", value, "gradeName");
            return (Criteria) this;
        }

        public Criteria andGradeNameGreaterThanOrEqualTo(String value) {
            addCriterion("grade_name >=", value, "gradeName");
            return (Criteria) this;
        }

        public Criteria andGradeNameLessThan(String value) {
            addCriterion("grade_name <", value, "gradeName");
            return (Criteria) this;
        }

        public Criteria andGradeNameLessThanOrEqualTo(String value) {
            addCriterion("grade_name <=", value, "gradeName");
            return (Criteria) this;
        }

        public Criteria andGradeNameLike(String value) {
            addCriterion("grade_name like", value, "gradeName");
            return (Criteria) this;
        }

        public Criteria andGradeNameNotLike(String value) {
            addCriterion("grade_name not like", value, "gradeName");
            return (Criteria) this;
        }

        public Criteria andGradeNameIn(List<String> values) {
            addCriterion("grade_name in", values, "gradeName");
            return (Criteria) this;
        }

        public Criteria andGradeNameNotIn(List<String> values) {
            addCriterion("grade_name not in", values, "gradeName");
            return (Criteria) this;
        }

        public Criteria andGradeNameBetween(String value1, String value2) {
            addCriterion("grade_name between", value1, value2, "gradeName");
            return (Criteria) this;
        }

        public Criteria andGradeNameNotBetween(String value1, String value2) {
            addCriterion("grade_name not between", value1, value2, "gradeName");
            return (Criteria) this;
        }

        public Criteria andGradePointIsNull() {
            addCriterion("grade_point is null");
            return (Criteria) this;
        }

        public Criteria andGradePointIsNotNull() {
            addCriterion("grade_point is not null");
            return (Criteria) this;
        }

        public Criteria andGradePointEqualTo(Integer value) {
            addCriterion("grade_point =", value, "gradePoint");
            return (Criteria) this;
        }

        public Criteria andGradePointNotEqualTo(Integer value) {
            addCriterion("grade_point <>", value, "gradePoint");
            return (Criteria) this;
        }

        public Criteria andGradePointGreaterThan(Integer value) {
            addCriterion("grade_point >", value, "gradePoint");
            return (Criteria) this;
        }

        public Criteria andGradePointGreaterThanOrEqualTo(Integer value) {
            addCriterion("grade_point >=", value, "gradePoint");
            return (Criteria) this;
        }

        public Criteria andGradePointLessThan(Integer value) {
            addCriterion("grade_point <", value, "gradePoint");
            return (Criteria) this;
        }

        public Criteria andGradePointLessThanOrEqualTo(Integer value) {
            addCriterion("grade_point <=", value, "gradePoint");
            return (Criteria) this;
        }

        public Criteria andGradePointIn(List<Integer> values) {
            addCriterion("grade_point in", values, "gradePoint");
            return (Criteria) this;
        }

        public Criteria andGradePointNotIn(List<Integer> values) {
            addCriterion("grade_point not in", values, "gradePoint");
            return (Criteria) this;
        }

        public Criteria andGradePointBetween(Integer value1, Integer value2) {
            addCriterion("grade_point between", value1, value2, "gradePoint");
            return (Criteria) this;
        }

        public Criteria andGradePointNotBetween(Integer value1, Integer value2) {
            addCriterion("grade_point not between", value1, value2, "gradePoint");
            return (Criteria) this;
        }

        public Criteria andGradeGoldIsNull() {
            addCriterion("grade_gold is null");
            return (Criteria) this;
        }

        public Criteria andGradeGoldIsNotNull() {
            addCriterion("grade_gold is not null");
            return (Criteria) this;
        }

        public Criteria andGradeGoldEqualTo(Integer value) {
            addCriterion("grade_gold =", value, "gradeGold");
            return (Criteria) this;
        }

        public Criteria andGradeGoldNotEqualTo(Integer value) {
            addCriterion("grade_gold <>", value, "gradeGold");
            return (Criteria) this;
        }

        public Criteria andGradeGoldGreaterThan(Integer value) {
            addCriterion("grade_gold >", value, "gradeGold");
            return (Criteria) this;
        }

        public Criteria andGradeGoldGreaterThanOrEqualTo(Integer value) {
            addCriterion("grade_gold >=", value, "gradeGold");
            return (Criteria) this;
        }

        public Criteria andGradeGoldLessThan(Integer value) {
            addCriterion("grade_gold <", value, "gradeGold");
            return (Criteria) this;
        }

        public Criteria andGradeGoldLessThanOrEqualTo(Integer value) {
            addCriterion("grade_gold <=", value, "gradeGold");
            return (Criteria) this;
        }

        public Criteria andGradeGoldIn(List<Integer> values) {
            addCriterion("grade_gold in", values, "gradeGold");
            return (Criteria) this;
        }

        public Criteria andGradeGoldNotIn(List<Integer> values) {
            addCriterion("grade_gold not in", values, "gradeGold");
            return (Criteria) this;
        }

        public Criteria andGradeGoldBetween(Integer value1, Integer value2) {
            addCriterion("grade_gold between", value1, value2, "gradeGold");
            return (Criteria) this;
        }

        public Criteria andGradeGoldNotBetween(Integer value1, Integer value2) {
            addCriterion("grade_gold not between", value1, value2, "gradeGold");
            return (Criteria) this;
        }

        public Criteria andGradeValueIsNull() {
            addCriterion("grade_value is null");
            return (Criteria) this;
        }

        public Criteria andGradeValueIsNotNull() {
            addCriterion("grade_value is not null");
            return (Criteria) this;
        }

        public Criteria andGradeValueEqualTo(Integer value) {
            addCriterion("grade_value =", value, "gradeValue");
            return (Criteria) this;
        }

        public Criteria andGradeValueNotEqualTo(Integer value) {
            addCriterion("grade_value <>", value, "gradeValue");
            return (Criteria) this;
        }

        public Criteria andGradeValueGreaterThan(Integer value) {
            addCriterion("grade_value >", value, "gradeValue");
            return (Criteria) this;
        }

        public Criteria andGradeValueGreaterThanOrEqualTo(Integer value) {
            addCriterion("grade_value >=", value, "gradeValue");
            return (Criteria) this;
        }

        public Criteria andGradeValueLessThan(Integer value) {
            addCriterion("grade_value <", value, "gradeValue");
            return (Criteria) this;
        }

        public Criteria andGradeValueLessThanOrEqualTo(Integer value) {
            addCriterion("grade_value <=", value, "gradeValue");
            return (Criteria) this;
        }

        public Criteria andGradeValueIn(List<Integer> values) {
            addCriterion("grade_value in", values, "gradeValue");
            return (Criteria) this;
        }

        public Criteria andGradeValueNotIn(List<Integer> values) {
            addCriterion("grade_value not in", values, "gradeValue");
            return (Criteria) this;
        }

        public Criteria andGradeValueBetween(Integer value1, Integer value2) {
            addCriterion("grade_value between", value1, value2, "gradeValue");
            return (Criteria) this;
        }

        public Criteria andGradeValueNotBetween(Integer value1, Integer value2) {
            addCriterion("grade_value not between", value1, value2, "gradeValue");
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