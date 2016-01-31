package com.innovista.core.survey.model;

import java.util.Comparator;

public class ColumnPositionCompartor implements Comparator<SurveyGridsColumns>
{
    public int compare(SurveyGridsColumns surveyGridsColumns1, SurveyGridsColumns surveyGridsColumns2)
    {
        if(surveyGridsColumns1.getColumnPosition()>surveyGridsColumns2.getColumnPosition())
        	return 1;
        else 
        	return -1;
    }
}


