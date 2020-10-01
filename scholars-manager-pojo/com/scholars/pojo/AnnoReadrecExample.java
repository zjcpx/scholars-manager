package com.scholars.pojo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AnnoReadrecExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public AnnoReadrecExample() {
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

        public Criteria andUsernoIsNull() {
            addCriterion("userNo is null");
            return (Criteria) this;
        }

        public Criteria andUsernoIsNotNull() {
            addCriterion("userNo is not null");
            return (Criteria) this;
        }

        public Criteria andUsernoEqualTo(String value) {
            addCriterion("userNo =", value, "userno");
            return (Criteria) this;
        }

        public Criteria andUsernoNotEqualTo(String value) {
            addCriterion("userNo <>", value, "userno");
            return (Criteria) this;
        }

        public Criteria andUsernoGreaterThan(String value) {
            addCriterion("userNo >", value, "userno");
            return (Criteria) this;
        }

        public Criteria andUsernoGreaterThanOrEqualTo(String value) {
            addCriterion("userNo >=", value, "userno");
            return (Criteria) this;
        }

        public Criteria andUsernoLessThan(String value) {
            addCriterion("userNo <", value, "userno");
            return (Criteria) this;
        }

        public Criteria andUsernoLessThanOrEqualTo(String value) {
            addCriterion("userNo <=", value, "userno");
            return (Criteria) this;
        }

        public Criteria andUsernoLike(String value) {
            addCriterion("userNo like", value, "userno");
            return (Criteria) this;
        }

        public Criteria andUsernoNotLike(String value) {
            addCriterion("userNo not like", value, "userno");
            return (Criteria) this;
        }

        public Criteria andUsernoIn(List<String> values) {
            addCriterion("userNo in", values, "userno");
            return (Criteria) this;
        }

        public Criteria andUsernoNotIn(List<String> values) {
            addCriterion("userNo not in", values, "userno");
            return (Criteria) this;
        }

        public Criteria andUsernoBetween(String value1, String value2) {
            addCriterion("userNo between", value1, value2, "userno");
            return (Criteria) this;
        }

        public Criteria andUsernoNotBetween(String value1, String value2) {
            addCriterion("userNo not between", value1, value2, "userno");
            return (Criteria) this;
        }

        public Criteria andGoodbadIsNull() {
            addCriterion("goodbad is null");
            return (Criteria) this;
        }

        public Criteria andGoodbadIsNotNull() {
            addCriterion("goodbad is not null");
            return (Criteria) this;
        }

        public Criteria andGoodbadEqualTo(Integer value) {
            addCriterion("goodbad =", value, "goodbad");
            return (Criteria) this;
        }

        public Criteria andGoodbadNotEqualTo(Integer value) {
            addCriterion("goodbad <>", value, "goodbad");
            return (Criteria) this;
        }

        public Criteria andGoodbadGreaterThan(Integer value) {
            addCriterion("goodbad >", value, "goodbad");
            return (Criteria) this;
        }

        public Criteria andGoodbadGreaterThanOrEqualTo(Integer value) {
            addCriterion("goodbad >=", value, "goodbad");
            return (Criteria) this;
        }

        public Criteria andGoodbadLessThan(Integer value) {
            addCriterion("goodbad <", value, "goodbad");
            return (Criteria) this;
        }

        public Criteria andGoodbadLessThanOrEqualTo(Integer value) {
            addCriterion("goodbad <=", value, "goodbad");
            return (Criteria) this;
        }

        public Criteria andGoodbadIn(List<Integer> values) {
            addCriterion("goodbad in", values, "goodbad");
            return (Criteria) this;
        }

        public Criteria andGoodbadNotIn(List<Integer> values) {
            addCriterion("goodbad not in", values, "goodbad");
            return (Criteria) this;
        }

        public Criteria andGoodbadBetween(Integer value1, Integer value2) {
            addCriterion("goodbad between", value1, value2, "goodbad");
            return (Criteria) this;
        }

        public Criteria andGoodbadNotBetween(Integer value1, Integer value2) {
            addCriterion("goodbad not between", value1, value2, "goodbad");
            return (Criteria) this;
        }

        public Criteria andAnnouncementidIsNull() {
            addCriterion("announcementId is null");
            return (Criteria) this;
        }

        public Criteria andAnnouncementidIsNotNull() {
            addCriterion("announcementId is not null");
            return (Criteria) this;
        }

        public Criteria andAnnouncementidEqualTo(Long value) {
            addCriterion("announcementId =", value, "announcementid");
            return (Criteria) this;
        }

        public Criteria andAnnouncementidNotEqualTo(Long value) {
            addCriterion("announcementId <>", value, "announcementid");
            return (Criteria) this;
        }

        public Criteria andAnnouncementidGreaterThan(Long value) {
            addCriterion("announcementId >", value, "announcementid");
            return (Criteria) this;
        }

        public Criteria andAnnouncementidGreaterThanOrEqualTo(Long value) {
            addCriterion("announcementId >=", value, "announcementid");
            return (Criteria) this;
        }

        public Criteria andAnnouncementidLessThan(Long value) {
            addCriterion("announcementId <", value, "announcementid");
            return (Criteria) this;
        }

        public Criteria andAnnouncementidLessThanOrEqualTo(Long value) {
            addCriterion("announcementId <=", value, "announcementid");
            return (Criteria) this;
        }

        public Criteria andAnnouncementidIn(List<Long> values) {
            addCriterion("announcementId in", values, "announcementid");
            return (Criteria) this;
        }

        public Criteria andAnnouncementidNotIn(List<Long> values) {
            addCriterion("announcementId not in", values, "announcementid");
            return (Criteria) this;
        }

        public Criteria andAnnouncementidBetween(Long value1, Long value2) {
            addCriterion("announcementId between", value1, value2, "announcementid");
            return (Criteria) this;
        }

        public Criteria andAnnouncementidNotBetween(Long value1, Long value2) {
            addCriterion("announcementId not between", value1, value2, "announcementid");
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