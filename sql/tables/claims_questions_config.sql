create table claims_questions(
    QUESTION_ID integer,
    RISK_ID varchar(25),
    CLAIM_TYPE_ID numeric,
    QUESTION varchar(1000),
    DEPENDS_ON_QUESTION_ID numeric,
    TRUE_CONSEQUENCE_MULTIPLIER numeric,
    FALSE_CONSEQUENCE_MULTIPLIER numeric,
    CLAIM_REJECTED varchar(25),
    VERSION varchar(15)
    );

