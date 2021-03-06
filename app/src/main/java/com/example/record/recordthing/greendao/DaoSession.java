package com.example.record.recordthing.greendao;

import java.util.Map;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.AbstractDaoSession;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.identityscope.IdentityScopeType;
import org.greenrobot.greendao.internal.DaoConfig;

import com.example.record.recordthing.bean.QuestionBank;
import com.example.record.recordthing.bean.AnswerData;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.

/**
 * {@inheritDoc}
 * 
 * @see org.greenrobot.greendao.AbstractDaoSession
 */
public class DaoSession extends AbstractDaoSession {

    private final DaoConfig questionBankDaoConfig;
    private final DaoConfig answerDataDaoConfig;

    private final QuestionBankDao questionBankDao;
    private final AnswerDataDao answerDataDao;

    public DaoSession(Database db, IdentityScopeType type, Map<Class<? extends AbstractDao<?, ?>>, DaoConfig>
            daoConfigMap) {
        super(db);

        questionBankDaoConfig = daoConfigMap.get(QuestionBankDao.class).clone();
        questionBankDaoConfig.initIdentityScope(type);

        answerDataDaoConfig = daoConfigMap.get(AnswerDataDao.class).clone();
        answerDataDaoConfig.initIdentityScope(type);

        questionBankDao = new QuestionBankDao(questionBankDaoConfig, this);
        answerDataDao = new AnswerDataDao(answerDataDaoConfig, this);

        registerDao(QuestionBank.class, questionBankDao);
        registerDao(AnswerData.class, answerDataDao);
    }
    
    public void clear() {
        questionBankDaoConfig.clearIdentityScope();
        answerDataDaoConfig.clearIdentityScope();
    }

    public QuestionBankDao getQuestionBankDao() {
        return questionBankDao;
    }

    public AnswerDataDao getAnswerDataDao() {
        return answerDataDao;
    }

}
