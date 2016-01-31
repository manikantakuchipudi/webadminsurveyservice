package com.innovista.core.refelection;

public class LoadModelClasses {
	
	
	public static void main(String[] args)
	{
		String sql="SELECT "
				+ "member.org_id AS eligcustId, "
				+ "member.member_grp_name AS eligcustName, "
				+ "cot.class_of_trade AS COT, "
				+ "TO_CHAR(eligcust.start_date, ''MM/DD/YYYY'') as startDate, "
				+ "TO_CHAR(eligcust.end_date, ''MM/DD/YYYY'') as endDate, "
				+ "CASE "
				+ "  WHEN strategy.module_type <> ''PRICING'' THEN ''N/A'' "
				+ "  WHEN cmt.tier_idx = -2 THEN ''Pending'' "
				+ "  WHEN cmt.tier_idx = -1 THEN ''Not Committed'' "
				+ "  ELSE CAST((cmt.tier_idx + 1) AS CHAR) "
				+ "END as tierIndex, "
				+ "CASE "
				+ "WHEN strategy.module_type = ''INCENTV'' OR strategy.module_type = ''MCO'' THEN ''Rebate'' "
				+ "  WHEN strategy.module_type = ''PRICING'' THEN ''Price'' "
				+ "  ELSE '''' "
				+ "END as programType, "
				+ "NVL(strategy.name, '''') AS programName, "
				+ "CASE "
				+ "  WHEN comp.is_qualification_bucket = 1 THEN ''Tier Basis'' "
				+ "  WHEN comp.is_qualification_bucket = 0 THEN ''Benefit'' "
				+ "  ELSE CAST(comp.is_qualification_bucket AS CHAR) "
				+ "END as componentType, "
				+ "NVL(comp.name, '''') AS componentName, "
				+ "NVL(eligcust.filter_name, '''') AS filterName, "
				+ "CASE "
				+ " WHEN cmt.is_access_price_flag = 1 THEN '''' "
				+ "ELSE NVL(owner.org_id, '''') "
				+ "END AS committedCustomerId, "
				+ "CASE "
				+ " WHEN cmt.is_access_price_flag = 1 THEN ''''  "
				+ " ELSE NVL(owner.member_grp_name, '''') "
				+ " END AS committedCustomerName "
				+ "FROM "
				+ "mn_cf_eligible_customer eligcust "
				+ "INNER JOIN mn_cf_bucket bucket ON (eligcust.bucket_id = bucket.cf_bucket_id "
				+ " AND eligcust.bucket_mgr_id = bucket.mgr_id "
				+ "    AND eligcust.root_src_id =[?:1]) "
				+ "INNER JOIN mn_cf_component comp ON (bucket.bucket_src_id = comp.cf_component_id) "
				+ " INNER JOIN mn_cf_period period ON (eligcust.period_id = period.cf_period_id "
				+ "AND eligcust.period_mgr_id = period.mgr_id"
				+ "AND eligcust.root_src_id =[?:1]) "
				+ "INNER JOIN mn_cf_strategy strategy ON (period.src_id = strategy.cf_strategy_id) "
				+ " INNER JOIN mn_member member ON (eligcust.member_id = member.member_id "
				+ "AND eligcust.root_src_id =[?:1]) "
				+ "LEFT OUTER JOIN mn_commitment cmt ON (period.commit_id = cmt.commit_id) "
				+ "LEFT OUTER JOIN mn_member owner ON (cmt.owner_member_id = owner.member_id) "
				+ "LEFT OUTER JOIN mn_org_cot cot ON "
				+ "(eligcust.member_id = cot.org_id AND "
				+ " cot.eff_start_date <= (CAST((SYSTIMESTAMP AT TIME ZONE ''${com.modeln._default.timeZone}'') AS TIMESTAMP)) AND "
				+ " cot.eff_end_date >= (CAST((SYSTIMESTAMP AT TIME ZONE ''${com.modeln._default.timeZone}'') AS TIMESTAMP)) ) "
				+ "WHERE "
				+ "ligcust.eligible_customer_id IN ( [SQ:ExportFilter:] )"; 
	}
	
	
	

}
