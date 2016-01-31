package com.innovista.db.util;

import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class DBUtil {

	public static List<Map> getRecordsFromDB(SessionFactory sessionFactory,String query){
		List<Map> listRecords = null;
		Session session =sessionFactory.openSession();
		
		try
		{
			System.out.println("Query Executed DB : " + query);
			SQLQuery sqlQuery = session.createSQLQuery(query);
			sqlQuery.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
			listRecords = sqlQuery.list();
			System.out.println("getRecordsFromDB : "+query);
		} finally {
			session.close();
		}
		return listRecords;
	}

	public static List<Map<String,Object>> getRecordsFromDBObj(SessionFactory sessionFactory,String query){
		List<Map<String,Object>> listRecords = null;
		Session session =sessionFactory.openSession();

		try
		{
			System.out.println("getCompanyUsersDetails : " + query);
			SQLQuery sqlQuery = session.createSQLQuery(query);
			sqlQuery.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
			listRecords = sqlQuery.list();
			System.out.println("getRecordsFromDB : "+query);
		} finally {
			session.close();
		}
		return listRecords;
	}



	
	
}