package com.scholars.pojo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AnnoCommontsExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public AnnoCommontsExample() {
        oredCriteria = new ArrayList<Criteria>();
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
            criteria = new ArrayList<Criterion>();
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

        public Criteria andIdEqualTo(Long value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Long value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Long value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Long value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Long value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Long value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Long> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Long> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Long value1, Long value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Long value1, Long value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andAnnouncenameIsNull() {
            addCriterion("announceName is null");
            return (Criteria) this;
        }

        public Criteria andAnnouncenameIsNotNull() {
            addCriterion("announceName is not null");
            return (Criteria) this;
        }

        public Criteria andAnnouncenameEqualTo(String value) {
            addCriterion("announceName =", value, "announcename");
            return (Criteria) this;
        }

        public Criteria andAnnouncenameNotEqualTo(String value) {
            addCriterion("announceName <>", value, "announcename");
            return (Criteria) this;
        }

        public Criteria andAnnouncenameGreaterThan(String value) {
            addCriterion("announceName >", value, "announcename");
            return (Criteria) this;
        }

        public Criteria andAnnouncenameGreaterThanOrEqualTo(String value) {
            addCriterion("announceName >=", value, "announcename");
            return (Criteria) this;
        }

        public Criteria andAnnouncenameLessThan(String value) {
            addCriterion("announceName <", value, "announcename");
            return (Criteria) this;
        }

        public Criteria andAnnouncenameLessThanOrEqualTo(String value) {
            addCriterion("announceName <=", value, "announcename");
            return (Criteria) this;
        }

        public Criteria andAnnouncenameLike(String value) {
            addCriterion("announceName like", value, "announcename");
            return (Criteria) this;
        }

        public Criteria andAnnouncenameNotLike(String value) {
            addCriterion("announceName not like", value, "announcename");
            return (Criteria) this;
        }

        public Criteria andAnnouncenameIn(List<String> values) {
            addCriterion("announceName in", values, "announcename");
            return (Criteria) this;
        }

        public Criteria andAnnouncenameNotIn(List<String> values) {
            addCriterion("announceName not in", values, "announcename");
            return (Criteria) this;
        }

        public Criteria andAnnouncenameBetween(String value1, String value2) {
            addCriterion("announceName between", value1, value2, "announcename");
            return (Criteria) this;
        }

        public Criteria andAnnouncenameNotBetween(String value1, String value2) {
            addCriterion("announceName not between", value1, value2, "announcename");
            return (Criteria) this;
        }

        public Criteria andCommuserIsNull() {
            addCriterion("commUser is null");
            return (Criteria) this;
        }

        public Criteria andCommuserIsNotNull() {
            addCriterion("commUser is not null");
            return (Criteria) this;
        }

        public Criteria andCommuserEqualTo(String value) {
            addCriterion("commUser =", value, "commuser");
            return (Criteria) this;
        }

        public Criteria andCommuserNotEqualTo(String value) {
            addCriterion("commUser <>", value, "commuser");
            return (Criteria) this;
        }

        public Criteria andCommuserGreaterThan(String value) {
            addCriterion("commUser >", value, "commuser");
            return (Criteria) this;
        }

        public Criteria andCommuserGreaterThanOrEqualTo(String value) {
            addCriterion("commUser >=", value, "commuser");
            return (Criteria) this;
        }

        public Criteria andCommuserLessThan(String value) {
            addCriterion("commUser <", value, "commuser");
            return (Criteria) this;
        }

        public Criteria andCommuserLessThanOrEqualTo(String value) {
            addCriterion("commUser <=", value, "commuser");
            return (Criteria) this;
        }

        public Criteria andCommuserLike(String value) {
            addCriterion("commUser like", value, "commuser");
            return (Criteria) this;
        }

        public Criteria andCommuserNotLike(String value) {
            addCriterion("commUser not like", value, "commuser");
            return (Criteria) this;
        }

        public Criteria andCommuserIn(List<String> values) {
            addCriterion("commUser in", values, "commuser");
            return (Criteria) this;
        }

        public Criteria andCommuserNotIn(List<String> values) {
            addCriterion("commUser not in", values, "commuser");
            return (Criteria) this;
        }

        public Criteria andCommuserBetween(String value1, String value2) {
            addCriterion("commUser between", value1, value2, "commuser");
            return (Criteria) this;
        }

        public Criteria andCommuserNotBetween(String value1, String value2) {
            addCriterion("commUser not between", value1, value2, "commuser");
            return (Criteria) this;
        }

        public Criteria andCommtypeIsNull() {
            addCriterion("commType is null");
            return (Criteria) this;
        }

        public Criteria andCommtypeIsNotNull() {
            addCriterion("commType is not null");
            return (Criteria) this;
        }

        public Criteria andCommtypeEqualTo(String value) {
            addCriterion("commType =", value, "commtype");
            return (Criteria) this;
        }

        public Criteria andCommtypeNotEqualTo(String value) {
            addCriterion("commType <>", value, "commtype");
            return (Criteria) this;
        }

        public Criteria andCommtypeGreaterThan(String value) {
            addCriterion("commType >", value, "commtype");
            return (Criteria) this;
        }

        public Criteria andCommtypeGreaterThanOrEqualTo(String value) {
            addCriterion("commType >=", value, "commtype");
            return (Criteria) this;
        }

        public Criteria andCommtypeLessThan(String value) {
            addCriterion("commType <", value, "commtype");
            return (Criteria) this;
        }

        public Criteria andCommtypeLessThanOrEqualTo(String value) {
            addCriterion("commType <=", value, "commtype");
            return (Criteria) this;
        }

        public Criteria andCommtypeLike(String value) {
            addCriterion("commType like", value, "commtype");
            return (Criteria) this;
        }

        public Criteria andCommtypeNotLike(String value) {
            addCriterion("commType not like", value, "commtype");
            return (Criteria) this;
        }

        public Criteria andCommtypeIn(List<String> values) {
            addCriterion("commType in", values, "commtype");
            return (Criteria) this;
        }

        public Criteria andCommtypeNotIn(List<String> values) {
            addCriterion("commType not in", values, "commtype");
            return (Criteria) this;
        }

        public Criteria andCommtypeBetween(String value1, String value2) {
            addCriterion("commType between", value1, value2, "commtype");
            return (Criteria) this;
        }

        public Criteria andCommtypeNotBetween(String value1, String value2) {
            addCriterion("commType not between", value1, value2, "commtype");
            return (Criteria) this;
        }

        public Criteria andCreatetimeIsNull() {
            addCriterion("createTime is null");
            return (Criteria) this;
        }

        public Criteria andCreatetimeIsNotNull() {
            addCriterion("createTime is not null");
            return (Criteria) this;
        }

        public Criteria andCreatetimeEqualTo(Date value) {
            addCriterion("createTime =", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeNotEqualTo(Date value) {
            addCriterion("createTime <>", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeGreaterThan(Date value) {
            addCriterion("createTime >", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeGreaterThanOrEqualTo(Date value) {
            addCriterion("createTime >=", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeLessThan(Date value) {
            addCriterion("createTime <", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeLessThanOrEqualTo(Date value) {
            addCriterion("createTime <=", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeIn(List<Date> values) {
            addCriterion("createTime in", values, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeNotIn(List<Date> values) {
            addCriterion("createTime not in", values, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeBetween(Date value1, Date value2) {
            addCriterion("createTime between", value1, value2, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeNotBetween(Date value1, Date value2) {
            addCriterion("createTime not between", value1, value2, "createtime");
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