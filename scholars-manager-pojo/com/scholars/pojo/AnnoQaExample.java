package com.scholars.pojo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AnnoQaExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public AnnoQaExample() {
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

        public Criteria andQuestioncontentIsNull() {
            addCriterion("questionContent is null");
            return (Criteria) this;
        }

        public Criteria andQuestioncontentIsNotNull() {
            addCriterion("questionContent is not null");
            return (Criteria) this;
        }

        public Criteria andQuestioncontentEqualTo(String value) {
            addCriterion("questionContent =", value, "questioncontent");
            return (Criteria) this;
        }

        public Criteria andQuestioncontentNotEqualTo(String value) {
            addCriterion("questionContent <>", value, "questioncontent");
            return (Criteria) this;
        }

        public Criteria andQuestioncontentGreaterThan(String value) {
            addCriterion("questionContent >", value, "questioncontent");
            return (Criteria) this;
        }

        public Criteria andQuestioncontentGreaterThanOrEqualTo(String value) {
            addCriterion("questionContent >=", value, "questioncontent");
            return (Criteria) this;
        }

        public Criteria andQuestioncontentLessThan(String value) {
            addCriterion("questionContent <", value, "questioncontent");
            return (Criteria) this;
        }

        public Criteria andQuestioncontentLessThanOrEqualTo(String value) {
            addCriterion("questionContent <=", value, "questioncontent");
            return (Criteria) this;
        }

        public Criteria andQuestioncontentLike(String value) {
            addCriterion("questionContent like", value, "questioncontent");
            return (Criteria) this;
        }

        public Criteria andQuestioncontentNotLike(String value) {
            addCriterion("questionContent not like", value, "questioncontent");
            return (Criteria) this;
        }

        public Criteria andQuestioncontentIn(List<String> values) {
            addCriterion("questionContent in", values, "questioncontent");
            return (Criteria) this;
        }

        public Criteria andQuestioncontentNotIn(List<String> values) {
            addCriterion("questionContent not in", values, "questioncontent");
            return (Criteria) this;
        }

        public Criteria andQuestioncontentBetween(String value1, String value2) {
            addCriterion("questionContent between", value1, value2, "questioncontent");
            return (Criteria) this;
        }

        public Criteria andQuestioncontentNotBetween(String value1, String value2) {
            addCriterion("questionContent not between", value1, value2, "questioncontent");
            return (Criteria) this;
        }

        public Criteria andQuestiontimeIsNull() {
            addCriterion("questionTime is null");
            return (Criteria) this;
        }

        public Criteria andQuestiontimeIsNotNull() {
            addCriterion("questionTime is not null");
            return (Criteria) this;
        }

        public Criteria andQuestiontimeEqualTo(Date value) {
            addCriterion("questionTime =", value, "questiontime");
            return (Criteria) this;
        }

        public Criteria andQuestiontimeNotEqualTo(Date value) {
            addCriterion("questionTime <>", value, "questiontime");
            return (Criteria) this;
        }

        public Criteria andQuestiontimeGreaterThan(Date value) {
            addCriterion("questionTime >", value, "questiontime");
            return (Criteria) this;
        }

        public Criteria andQuestiontimeGreaterThanOrEqualTo(Date value) {
            addCriterion("questionTime >=", value, "questiontime");
            return (Criteria) this;
        }

        public Criteria andQuestiontimeLessThan(Date value) {
            addCriterion("questionTime <", value, "questiontime");
            return (Criteria) this;
        }

        public Criteria andQuestiontimeLessThanOrEqualTo(Date value) {
            addCriterion("questionTime <=", value, "questiontime");
            return (Criteria) this;
        }

        public Criteria andQuestiontimeIn(List<Date> values) {
            addCriterion("questionTime in", values, "questiontime");
            return (Criteria) this;
        }

        public Criteria andQuestiontimeNotIn(List<Date> values) {
            addCriterion("questionTime not in", values, "questiontime");
            return (Criteria) this;
        }

        public Criteria andQuestiontimeBetween(Date value1, Date value2) {
            addCriterion("questionTime between", value1, value2, "questiontime");
            return (Criteria) this;
        }

        public Criteria andQuestiontimeNotBetween(Date value1, Date value2) {
            addCriterion("questionTime not between", value1, value2, "questiontime");
            return (Criteria) this;
        }

        public Criteria andQuestionanswersIsNull() {
            addCriterion("questionAnswers is null");
            return (Criteria) this;
        }

        public Criteria andQuestionanswersIsNotNull() {
            addCriterion("questionAnswers is not null");
            return (Criteria) this;
        }

        public Criteria andQuestionanswersEqualTo(String value) {
            addCriterion("questionAnswers =", value, "questionanswers");
            return (Criteria) this;
        }

        public Criteria andQuestionanswersNotEqualTo(String value) {
            addCriterion("questionAnswers <>", value, "questionanswers");
            return (Criteria) this;
        }

        public Criteria andQuestionanswersGreaterThan(String value) {
            addCriterion("questionAnswers >", value, "questionanswers");
            return (Criteria) this;
        }

        public Criteria andQuestionanswersGreaterThanOrEqualTo(String value) {
            addCriterion("questionAnswers >=", value, "questionanswers");
            return (Criteria) this;
        }

        public Criteria andQuestionanswersLessThan(String value) {
            addCriterion("questionAnswers <", value, "questionanswers");
            return (Criteria) this;
        }

        public Criteria andQuestionanswersLessThanOrEqualTo(String value) {
            addCriterion("questionAnswers <=", value, "questionanswers");
            return (Criteria) this;
        }

        public Criteria andQuestionanswersLike(String value) {
            addCriterion("questionAnswers like", value, "questionanswers");
            return (Criteria) this;
        }

        public Criteria andQuestionanswersNotLike(String value) {
            addCriterion("questionAnswers not like", value, "questionanswers");
            return (Criteria) this;
        }

        public Criteria andQuestionanswersIn(List<String> values) {
            addCriterion("questionAnswers in", values, "questionanswers");
            return (Criteria) this;
        }

        public Criteria andQuestionanswersNotIn(List<String> values) {
            addCriterion("questionAnswers not in", values, "questionanswers");
            return (Criteria) this;
        }

        public Criteria andQuestionanswersBetween(String value1, String value2) {
            addCriterion("questionAnswers between", value1, value2, "questionanswers");
            return (Criteria) this;
        }

        public Criteria andQuestionanswersNotBetween(String value1, String value2) {
            addCriterion("questionAnswers not between", value1, value2, "questionanswers");
            return (Criteria) this;
        }

        public Criteria andAnnonameIsNull() {
            addCriterion("annoName is null");
            return (Criteria) this;
        }

        public Criteria andAnnonameIsNotNull() {
            addCriterion("annoName is not null");
            return (Criteria) this;
        }

        public Criteria andAnnonameEqualTo(String value) {
            addCriterion("annoName =", value, "annoname");
            return (Criteria) this;
        }

        public Criteria andAnnonameNotEqualTo(String value) {
            addCriterion("annoName <>", value, "annoname");
            return (Criteria) this;
        }

        public Criteria andAnnonameGreaterThan(String value) {
            addCriterion("annoName >", value, "annoname");
            return (Criteria) this;
        }

        public Criteria andAnnonameGreaterThanOrEqualTo(String value) {
            addCriterion("annoName >=", value, "annoname");
            return (Criteria) this;
        }

        public Criteria andAnnonameLessThan(String value) {
            addCriterion("annoName <", value, "annoname");
            return (Criteria) this;
        }

        public Criteria andAnnonameLessThanOrEqualTo(String value) {
            addCriterion("annoName <=", value, "annoname");
            return (Criteria) this;
        }

        public Criteria andAnnonameLike(String value) {
            addCriterion("annoName like", value, "annoname");
            return (Criteria) this;
        }

        public Criteria andAnnonameNotLike(String value) {
            addCriterion("annoName not like", value, "annoname");
            return (Criteria) this;
        }

        public Criteria andAnnonameIn(List<String> values) {
            addCriterion("annoName in", values, "annoname");
            return (Criteria) this;
        }

        public Criteria andAnnonameNotIn(List<String> values) {
            addCriterion("annoName not in", values, "annoname");
            return (Criteria) this;
        }

        public Criteria andAnnonameBetween(String value1, String value2) {
            addCriterion("annoName between", value1, value2, "annoname");
            return (Criteria) this;
        }

        public Criteria andAnnonameNotBetween(String value1, String value2) {
            addCriterion("annoName not between", value1, value2, "annoname");
            return (Criteria) this;
        }

        public Criteria andAnswertimeIsNull() {
            addCriterion("answerTime is null");
            return (Criteria) this;
        }

        public Criteria andAnswertimeIsNotNull() {
            addCriterion("answerTime is not null");
            return (Criteria) this;
        }

        public Criteria andAnswertimeEqualTo(Date value) {
            addCriterion("answerTime =", value, "answertime");
            return (Criteria) this;
        }

        public Criteria andAnswertimeNotEqualTo(Date value) {
            addCriterion("answerTime <>", value, "answertime");
            return (Criteria) this;
        }

        public Criteria andAnswertimeGreaterThan(Date value) {
            addCriterion("answerTime >", value, "answertime");
            return (Criteria) this;
        }

        public Criteria andAnswertimeGreaterThanOrEqualTo(Date value) {
            addCriterion("answerTime >=", value, "answertime");
            return (Criteria) this;
        }

        public Criteria andAnswertimeLessThan(Date value) {
            addCriterion("answerTime <", value, "answertime");
            return (Criteria) this;
        }

        public Criteria andAnswertimeLessThanOrEqualTo(Date value) {
            addCriterion("answerTime <=", value, "answertime");
            return (Criteria) this;
        }

        public Criteria andAnswertimeIn(List<Date> values) {
            addCriterion("answerTime in", values, "answertime");
            return (Criteria) this;
        }

        public Criteria andAnswertimeNotIn(List<Date> values) {
            addCriterion("answerTime not in", values, "answertime");
            return (Criteria) this;
        }

        public Criteria andAnswertimeBetween(Date value1, Date value2) {
            addCriterion("answerTime between", value1, value2, "answertime");
            return (Criteria) this;
        }

        public Criteria andAnswertimeNotBetween(Date value1, Date value2) {
            addCriterion("answerTime not between", value1, value2, "answertime");
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