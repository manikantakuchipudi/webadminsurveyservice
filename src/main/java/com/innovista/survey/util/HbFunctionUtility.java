package com.innovista.survey.util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.internal.SessionImpl;

import java.util.logging.Logger;

/**
 * Created by mkuchipudi on 29-09-2015.
 */
public class HbFunctionUtility {




   // Logger logger= Logger.
    public Object save(SessionFactory sesionfactory,Object object)
    {

        if(sesionfactory==null)
            sesionfactory=HibernateUtil.getSessionFactory();
        return save(sesionfactory.openSession(),object);

    }


    public Object save(Session session, Object object)
    {
        if(session==null)
        {
            session=HibernateUtil.getSessionFactory().openSession();
        }
        session.beginTransaction();
        session.save(object);
        return object;
    }


    public Object saveAndCommit(Session session, Object object)
    {
        if(session==null)
        {
            session=HibernateUtil.getSessionFactory().openSession();
        }
        session.beginTransaction();
        session.save(object);
        commit(session);
        return object;
    }

    public Object commit(Session session)
    {
        try {
            session.getTransaction().commit();
            return "";
        }
        catch (Exception exp)
        {
          exp.printStackTrace();
            return null;
        }
        finally {
            if(session!=null)
                session.close();
        }
    }

}
