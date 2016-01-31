package com.innovista.core.refelection;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.util.zip.Inflater;
import java.util.zip.InflaterInputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.opensaml.DefaultBootstrap;
import org.opensaml.xml.Configuration;
import org.opensaml.xml.XMLObject;
import org.opensaml.xml.io.Unmarshaller;
import org.opensaml.xml.io.UnmarshallerFactory;
import org.opensaml.xml.parse.BasicParserPool;
import org.opensaml.xml.util.Base64;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public class TestXml {
	
	
	static String s="PD94bWwgdmVyc2lvbj0iMS4wIiBlbmNvZGluZz0iVVRGLTgiPz48c2FtbDJwOlJlc3BvbnNlIHhtbG5zOnNhbWwycD0idXJuOm9hc2lzOm5hbWVzOnRjOlNBTUw6Mi4wOnByb3RvY29sIiBEZXN0aW5hdGlvbj0iaHR0cHM6Ly8ydWEzMDcxcHk0Ojk0NDMvc2FtbFNQL2FjcyIgSUQ9ImlkMTc3MTIyMzU5MjQwMTE5ODUyODQzMTY4OTIiIElzc3VlSW5zdGFudD0iMjAxNS0xMi0xMlQxODoyMzo0Ni42MTFaIiBWZXJzaW9uPSIyLjAiIHhtbG5zOnhzPSJodHRwOi8vd3d3LnczLm9yZy8yMDAxL1hNTFNjaGVtYSI+PHNhbWwyOklzc3VlciB4bWxuczpzYW1sMj0idXJuOm9hc2lzOm5hbWVzOnRjOlNBTUw6Mi4wOmFzc2VydGlvbiIgRm9ybWF0PSJ1cm46b2FzaXM6bmFtZXM6dGM6U0FNTDoyLjA6bmFtZWlkLWZvcm1hdDplbnRpdHkiPmh0dHA6Ly93d3cub2t0YS5jb20vZXhrNWhjNTdzMXdQcndpWVcwaDc8L3NhbWwyOklzc3Vlcj48ZHM6U2lnbmF0dXJlIHhtbG5zOmRzPSJodHRwOi8vd3d3LnczLm9yZy8yMDAwLzA5L3htbGRzaWcjIj48ZHM6U2lnbmVkSW5mbz48ZHM6Q2Fub25pY2FsaXphdGlvbk1ldGhvZCBBbGdvcml0aG09Imh0dHA6Ly93d3cudzMub3JnLzIwMDEvMTAveG1sLWV4Yy1jMTRuIyIvPjxkczpTaWduYXR1cmVNZXRob2QgQWxnb3JpdGhtPSJodHRwOi8vd3d3LnczLm9yZy8yMDAxLzA0L3htbGRzaWctbW9yZSNyc2Etc2hhMjU2Ii8+PGRzOlJlZmVyZW5jZSBVUkk9IiNpZDE3NzEyMjM1OTI0MDExOTg1Mjg0MzE2ODkyIj48ZHM6VHJhbnNmb3Jtcz48ZHM6VHJhbnNmb3JtIEFsZ29yaXRobT0iaHR0cDovL3d3dy53My5vcmcvMjAwMC8wOS94bWxkc2lnI2VudmVsb3BlZC1zaWduYXR1cmUiLz48ZHM6VHJhbnNmb3JtIEFsZ29yaXRobT0iaHR0cDovL3d3dy53My5vcmcvMjAwMS8xMC94bWwtZXhjLWMxNG4jIj48ZWM6SW5jbHVzaXZlTmFtZXNwYWNlcyB4bWxuczplYz0iaHR0cDovL3d3dy53My5vcmcvMjAwMS8xMC94bWwtZXhjLWMxNG4jIiBQcmVmaXhMaXN0PSJ4cyIvPjwvZHM6VHJhbnNmb3JtPjwvZHM6VHJhbnNmb3Jtcz48ZHM6RGlnZXN0TWV0aG9kIEFsZ29yaXRobT0iaHR0cDovL3d3dy53My5vcmcvMjAwMS8wNC94bWxlbmMjc2hhMjU2Ii8+PGRzOkRpZ2VzdFZhbHVlPnFqelFraTNYYWdMNUMyUGgyenlIZmk3UFkyV01mSVJLTnJxam8rdEkxdDA9PC9kczpEaWdlc3RWYWx1ZT48L2RzOlJlZmVyZW5jZT48L2RzOlNpZ25lZEluZm8+PGRzOlNpZ25hdHVyZVZhbHVlPlBkWDYxLzFKbDdhL3A5U1lWZlNuR2tXeEFNeG5Fc0grcmRkcDNBcGdoZXB0cjNpUkxTaUxKSnBsbllHWFpRVUMxbjFrNXViR1dpYlFYS01EcUlDbVZQYTZCcUtiVUo2RFkvTjRuNTNoUnZYOGZQcEpOQ0gwKzdpS1R3ZEJVa0duZWMrVFErd0pyZFdaTDlERng5Y0RFTTJRMWdObm1WQVg2bit2QnpuUTAxc1ltUFR4UTMvYlBEYlY3blJIeUk5Rm8xQmFMTmQrb1hzb0ZINmFPY1NSNWZ1ekpkSTEzN2IwTkNNYUpyR050RUtiTm44T29NQVVmdEhMVnRTaXdNOUlwVE9pcUE0OENWRExXS1c5L0ZFOGZnU0RmM2JRSE1FZUwrbHo0WURnVUZaUzVPdGlmUjIvLzBXVDdxMEhCLzJQOTNibm5GUWdORE44d2N5ZjV1eXc1dz09PC9kczpTaWduYXR1cmVWYWx1ZT48ZHM6S2V5SW5mbz48ZHM6WDUwOURhdGE+PGRzOlg1MDlDZXJ0aWZpY2F0ZT5NSUlEbWpDQ0FvS2dBd0lCQWdJR0FVN2xWbE1nTUEwR0NTcUdTSWIzRFFFQkJRVUFNSUdOTVFzd0NRWURWUVFHRXdKVlV6RVRNQkVHCkExVUVDQXdLUTJGc2FXWnZjbTVwWVRFV01CUUdBMVVFQnd3TlUyRnVJRVp5WVc1amFYTmpiekVOTUFzR0ExVUVDZ3dFVDJ0MFlURVUKTUJJR0ExVUVDd3dMVTFOUFVISnZkbWxrWlhJeERqQU1CZ05WQkFNTUJXSmpZbk5oTVJ3d0dnWUpLb1pJaHZjTkFRa0JGZzFwYm1adgpRRzlyZEdFdVkyOXRNQjRYRFRFMU1EY3pNVEU0TVRjek5sb1hEVFExTURjek1URTRNVGd6Tmxvd2dZMHhDekFKQmdOVkJBWVRBbFZUCk1STXdFUVlEVlFRSURBcERZV3hwWm05eWJtbGhNUll3RkFZRFZRUUhEQTFUWVc0Z1JuSmhibU5wYzJOdk1RMHdDd1lEVlFRS0RBUlAKYTNSaE1SUXdFZ1lEVlFRTERBdFRVMDlRY205MmFXUmxjakVPTUF3R0ExVUVBd3dGWW1OaWMyRXhIREFhQmdrcWhraUc5dzBCQ1FFVwpEV2x1Wm05QWIydDBZUzVqYjIwd2dnRWlNQTBHQ1NxR1NJYjNEUUVCQVFVQUE0SUJEd0F3Z2dFS0FvSUJBUUNSK0JCTEg2SE5Ja1RoCkM4TmQxTEJYRUR5UUlhSDIxQmpjN0MzRU5xWEF1VFd4MDFvK2l2QVhvbS92ZG1scDJuY3dicmUzclNRVHBGVk5vcG5BeG1kdk9tSm8KYjNqYlZKUDU3TzkvUFgybE1kRDBoNUZwNXNLNDVlUlNCK0VwSG9rdWp2Q294b3BDTmVoMGNVdnRybnBUUXovUlU0aTJsdmJOVWIvNwpvUCtkMk9Fay9kaXg1NW80bURSUlVSMEd4Y20xRXc1ZzlKdEZEaFVIT2RaRmcyZVBKbGNtRi8vblZGSjNZdEZWaVhFc3prdE9aRitzCjJ1TWJremR0QllWaExWZnBXb09qbFplT25BZDFWZjMxUjJtRmx3OU5sb21GS0hrUk5RUEV5WVVmTEMwZzZDMGgyZ3ZXTTAvOFN0SlcKV3c4aXd0c3lacjF1NWpmZzk4QnQzcGxQQWdNQkFBRXdEUVlKS29aSWh2Y05BUUVGQlFBRGdnRUJBSUdRVVFLbVNkekVTek5LQ3lrVQpDNzhwUWgzYUg4eUlMb3FkenJaMVRtZ1lXT0NaOXdTeXJ6cUNvTVowNGtqNW1uTWI2MUh6SXpZeHluNEVsNllveGwzTjFpSG5ycG91CjVSOEtPVytlZ0RwNjgySzVURkpnUXlPSHBMT2tGZkpIckx0MEpWQSs1S3dReWpXTGE4NWNmNXY1VE8xNkRRZG1vTTI4VHRHOC9QazEKV09PeWVSLzZMU2hDQVlVcTNrRzNDSUlDQS8yU3BjTS9pMlF2ZENjWDdFZW9JUWk4RmI3a0YvalcvczFKOUlUYndjaVNWY2x1bFRHRQpjT0w3aU1UV0ZHRjd5dlRaVWgrVEZGUG5jd1BqY3VhMVFJSTlrRXBqM2djZmIvMjVWQ2JYNUlha0tmOGVuS2lTVnhyZGNiUWJSM3ZjCklHZ1RaTFVDZG1TZWoyd3NEZm89PC9kczpYNTA5Q2VydGlmaWNhdGU+PC9kczpYNTA5RGF0YT48L2RzOktleUluZm8+PC9kczpTaWduYXR1cmU+PHNhbWwycDpTdGF0dXMgeG1sbnM6c2FtbDJwPSJ1cm46b2FzaXM6bmFtZXM6dGM6U0FNTDoyLjA6cHJvdG9jb2wiPjxzYW1sMnA6U3RhdHVzQ29kZSBWYWx1ZT0idXJuOm9hc2lzOm5hbWVzOnRjOlNBTUw6Mi4wOnN0YXR1czpTdWNjZXNzIi8+PC9zYW1sMnA6U3RhdHVzPjxzYW1sMjpBc3NlcnRpb24geG1sbnM6c2FtbDI9InVybjpvYXNpczpuYW1lczp0YzpTQU1MOjIuMDphc3NlcnRpb24iIElEPSJpZDE3NzEyMjM1OTI0MTEwOTgxNTQ2OTI0MDY5IiBJc3N1ZUluc3RhbnQ9IjIwMTUtMTItMTJUMTg6MjM6NDYuNjExWiIgVmVyc2lvbj0iMi4wIiB4bWxuczp4cz0iaHR0cDovL3d3dy53My5vcmcvMjAwMS9YTUxTY2hlbWEiPjxzYW1sMjpJc3N1ZXIgRm9ybWF0PSJ1cm46b2FzaXM6bmFtZXM6dGM6U0FNTDoyLjA6bmFtZWlkLWZvcm1hdDplbnRpdHkiIHhtbG5zOnNhbWwyPSJ1cm46b2FzaXM6bmFtZXM6dGM6U0FNTDoyLjA6YXNzZXJ0aW9uIj5odHRwOi8vd3d3Lm9rdGEuY29tL2V4azVoYzU3czF3UHJ3aVlXMGg3PC9zYW1sMjpJc3N1ZXI+PGRzOlNpZ25hdHVyZSB4bWxuczpkcz0iaHR0cDovL3d3dy53My5vcmcvMjAwMC8wOS94bWxkc2lnIyI+PGRzOlNpZ25lZEluZm8+PGRzOkNhbm9uaWNhbGl6YXRpb25NZXRob2QgQWxnb3JpdGhtPSJodHRwOi8vd3d3LnczLm9yZy8yMDAxLzEwL3htbC1leGMtYzE0biMiLz48ZHM6U2lnbmF0dXJlTWV0aG9kIEFsZ29yaXRobT0iaHR0cDovL3d3dy53My5vcmcvMjAwMS8wNC94bWxkc2lnLW1vcmUjcnNhLXNoYTI1NiIvPjxkczpSZWZlcmVuY2UgVVJJPSIjaWQxNzcxMjIzNTkyNDExMDk4MTU0NjkyNDA2OSI+PGRzOlRyYW5zZm9ybXM+PGRzOlRyYW5zZm9ybSBBbGdvcml0aG09Imh0dHA6Ly93d3cudzMub3JnLzIwMDAvMDkveG1sZHNpZyNlbnZlbG9wZWQtc2lnbmF0dXJlIi8+PGRzOlRyYW5zZm9ybSBBbGdvcml0aG09Imh0dHA6Ly93d3cudzMub3JnLzIwMDEvMTAveG1sLWV4Yy1jMTRuIyI+PGVjOkluY2x1c2l2ZU5hbWVzcGFjZXMgeG1sbnM6ZWM9Imh0dHA6Ly93d3cudzMub3JnLzIwMDEvMTAveG1sLWV4Yy1jMTRuIyIgUHJlZml4TGlzdD0ieHMiLz48L2RzOlRyYW5zZm9ybT48L2RzOlRyYW5zZm9ybXM+PGRzOkRpZ2VzdE1ldGhvZCBBbGdvcml0aG09Imh0dHA6Ly93d3cudzMub3JnLzIwMDEvMDQveG1sZW5jI3NoYTI1NiIvPjxkczpEaWdlc3RWYWx1ZT55TTJGeG1vaGJOYUljb3JsM1d4WWJKTWY1MHNpWVYyU1RtSjJ6S3k3UXJVPTwvZHM6RGlnZXN0VmFsdWU+PC9kczpSZWZlcmVuY2U+PC9kczpTaWduZWRJbmZvPjxkczpTaWduYXR1cmVWYWx1ZT5CZk9BOURQdXhENktNc2tVNjAxM3R6ckdoc25oSzcvdGdYOW5LY2tGdk0yMkphNFh0MVlSckRScm9odks5andtcTNrWEtkNThwajgvR3d4WXlLRENGT1gyL1FzOGNxT1Zkd09IS2JGK2VXc01YZzQ0d1RxOGMwbzc1aFlTbE4xaXdnL0hLM2RpNXAxM2V3UGt3bnlza3JsUXJPS0xQRkZDV3RUNittck9jYmJUOUZCODVBS1kycWh1M0FXcWhaMk9IdFRBTGZxWHVmWGcxVlRmVTU4WnJDZ0hWd3Z3bmJxcUMvZWNCMGt4eHlINzVRbzJvalJaQlpYL0ZSUFhHaVRheFNSU0RDR3hZNXpISHJrYm54cCs5ZEhyVWVROVdQdE92WUlHR1JSMlE2OEtob0JOZU5NaDJBTWhCMnlIbTAxcmFCdVp5SmNJUGI1bjJWaEE1NVhxZ3c9PTwvZHM6U2lnbmF0dXJlVmFsdWU+PGRzOktleUluZm8+PGRzOlg1MDlEYXRhPjxkczpYNTA5Q2VydGlmaWNhdGU+TUlJRG1qQ0NBb0tnQXdJQkFnSUdBVTdsVmxNZ01BMEdDU3FHU0liM0RRRUJCUVVBTUlHTk1Rc3dDUVlEVlFRR0V3SlZVekVUTUJFRwpBMVVFQ0F3S1EyRnNhV1p2Y201cFlURVdNQlFHQTFVRUJ3d05VMkZ1SUVaeVlXNWphWE5qYnpFTk1Bc0dBMVVFQ2d3RVQydDBZVEVVCk1CSUdBMVVFQ3d3TFUxTlBVSEp2ZG1sa1pYSXhEakFNQmdOVkJBTU1CV0pqWW5OaE1Sd3dHZ1lKS29aSWh2Y05BUWtCRmcxcGJtWnYKUUc5cmRHRXVZMjl0TUI0WERURTFNRGN6TVRFNE1UY3pObG9YRFRRMU1EY3pNVEU0TVRnek5sb3dnWTB4Q3pBSkJnTlZCQVlUQWxWVApNUk13RVFZRFZRUUlEQXBEWVd4cFptOXlibWxoTVJZd0ZBWURWUVFIREExVFlXNGdSbkpoYm1OcGMyTnZNUTB3Q3dZRFZRUUtEQVJQCmEzUmhNUlF3RWdZRFZRUUxEQXRUVTA5UWNtOTJhV1JsY2pFT01Bd0dBMVVFQXd3RlltTmljMkV4SERBYUJna3Foa2lHOXcwQkNRRVcKRFdsdVptOUFiMnQwWVM1amIyMHdnZ0VpTUEwR0NTcUdTSWIzRFFFQkFRVUFBNElCRHdBd2dnRUtBb0lCQVFDUitCQkxINkhOSWtUaApDOE5kMUxCWEVEeVFJYUgyMUJqYzdDM0VOcVhBdVRXeDAxbytpdkFYb20vdmRtbHAybmN3YnJlM3JTUVRwRlZOb3BuQXhtZHZPbUpvCmIzamJWSlA1N085L1BYMmxNZEQwaDVGcDVzSzQ1ZVJTQitFcEhva3VqdkNveG9wQ05laDBjVXZ0cm5wVFF6L1JVNGkybHZiTlViLzcKb1ArZDJPRWsvZGl4NTVvNG1EUlJVUjBHeGNtMUV3NWc5SnRGRGhVSE9kWkZnMmVQSmxjbUYvL25WRkozWXRGVmlYRXN6a3RPWkYrcwoydU1ia3pkdEJZVmhMVmZwV29PamxaZU9uQWQxVmYzMVIybUZsdzlObG9tRktIa1JOUVBFeVlVZkxDMGc2QzBoMmd2V00wLzhTdEpXCld3OGl3dHN5WnIxdTVqZmc5OEJ0M3BsUEFnTUJBQUV3RFFZSktvWklodmNOQVFFRkJRQURnZ0VCQUlHUVVRS21TZHpFU3pOS0N5a1UKQzc4cFFoM2FIOHlJTG9xZHpyWjFUbWdZV09DWjl3U3lyenFDb01aMDRrajVtbk1iNjFIekl6WXh5bjRFbDZZb3hsM04xaUhucnBvdQo1UjhLT1crZWdEcDY4Mks1VEZKZ1F5T0hwTE9rRmZKSHJMdDBKVkErNUt3UXlqV0xhODVjZjV2NVRPMTZEUWRtb00yOFR0RzgvUGsxCldPT3llUi82TFNoQ0FZVXEza0czQ0lJQ0EvMlNwY00vaTJRdmRDY1g3RWVvSVFpOEZiN2tGL2pXL3MxSjlJVGJ3Y2lTVmNsdWxUR0UKY09MN2lNVFdGR0Y3eXZUWlVoK1RGRlBuY3dQamN1YTFRSUk5a0VwajNnY2ZiLzI1VkNiWDVJYWtLZjhlbktpU1Z4cmRjYlFiUjN2YwpJR2dUWkxVQ2RtU2VqMndzRGZvPTwvZHM6WDUwOUNlcnRpZmljYXRlPjwvZHM6WDUwOURhdGE+PC9kczpLZXlJbmZvPjwvZHM6U2lnbmF0dXJlPjxzYW1sMjpTdWJqZWN0IHhtbG5zOnNhbWwyPSJ1cm46b2FzaXM6bmFtZXM6dGM6U0FNTDoyLjA6YXNzZXJ0aW9uIj48c2FtbDI6TmFtZUlEIEZvcm1hdD0idXJuOm9hc2lzOm5hbWVzOnRjOlNBTUw6MS4xOm5hbWVpZC1mb3JtYXQ6dW5zcGVjaWZpZWQiPnZlbmthdC5waW5naWxpLmNzQGJjYnNhLmNvbTwvc2FtbDI6TmFtZUlEPjxzYW1sMjpTdWJqZWN0Q29uZmlybWF0aW9uIE1ldGhvZD0idXJuOm9hc2lzOm5hbWVzOnRjOlNBTUw6Mi4wOmNtOmJlYXJlciI+PHNhbWwyOlN1YmplY3RDb25maXJtYXRpb25EYXRhIE5vdE9uT3JBZnRlcj0iMjAxNS0xMi0xMlQxODoyODo0Ni42MTFaIiBSZWNpcGllbnQ9Imh0dHBzOi8vMnVhMzA3MXB5NDo5NDQzL3NhbWxTUC9hY3MiLz48L3NhbWwyOlN1YmplY3RDb25maXJtYXRpb24+PC9zYW1sMjpTdWJqZWN0PjxzYW1sMjpDb25kaXRpb25zIE5vdEJlZm9yZT0iMjAxNS0xMi0xMlQxODoxODo0Ni42MTFaIiBOb3RPbk9yQWZ0ZXI9IjIwMTUtMTItMTJUMTg6Mjg6NDYuNjExWiIgeG1sbnM6c2FtbDI9InVybjpvYXNpczpuYW1lczp0YzpTQU1MOjIuMDphc3NlcnRpb24iPjxzYW1sMjpBdWRpZW5jZVJlc3RyaWN0aW9uPjxzYW1sMjpBdWRpZW5jZT5odHRwczovLzJ1YTMwNzFweTQ6OTQ0My9zYW1sU1AvYWNzPC9zYW1sMjpBdWRpZW5jZT48L3NhbWwyOkF1ZGllbmNlUmVzdHJpY3Rpb24+PC9zYW1sMjpDb25kaXRpb25zPjxzYW1sMjpBdXRoblN0YXRlbWVudCBBdXRobkluc3RhbnQ9IjIwMTUtMTItMTJUMTg6MjM6NDYuNjExWiIgU2Vzc2lvbkluZGV4PSJpZDE0NDk5NDQ2MjY2MTEuMjAyMTIyMTY1OSIgeG1sbnM6c2FtbDI9InVybjpvYXNpczpuYW1lczp0YzpTQU1MOjIuMDphc3NlcnRpb24iPjxzYW1sMjpBdXRobkNvbnRleHQ+PHNhbWwyOkF1dGhuQ29udGV4dENsYXNzUmVmPnVybjpvYXNpczpuYW1lczp0YzpTQU1MOjIuMDphYzpjbGFzc2VzOlBhc3N3b3JkUHJvdGVjdGVkVHJhbnNwb3J0PC9zYW1sMjpBdXRobkNvbnRleHRDbGFzc1JlZj48L3NhbWwyOkF1dGhuQ29udGV4dD48L3NhbWwyOkF1dGhuU3RhdGVtZW50PjxzYW1sMjpBdHRyaWJ1dGVTdGF0ZW1lbnQgeG1sbnM6c2FtbDI9InVybjpvYXNpczpuYW1lczp0YzpTQU1MOjIuMDphc3NlcnRpb24iPjxzYW1sMjpBdHRyaWJ1dGUgTmFtZT0iZW1haWwiIE5hbWVGb3JtYXQ9InVybjpvYXNpczpuYW1lczp0YzpTQU1MOjIuMDphdHRybmFtZS1mb3JtYXQ6dW5zcGVjaWZpZWQiPjxzYW1sMjpBdHRyaWJ1dGVWYWx1ZSB4bWxuczp4cz0iaHR0cDovL3d3dy53My5vcmcvMjAwMS9YTUxTY2hlbWEiIHhtbG5zOnhzaT0iaHR0cDovL3d3dy53My5vcmcvMjAwMS9YTUxTY2hlbWEtaW5zdGFuY2UiIHhzaTp0eXBlPSJ4czpzdHJpbmciPnZlbmthdC5waW5naWxpLmNzQGJjYnNhLmNvbTwvc2FtbDI6QXR0cmlidXRlVmFsdWU+PC9zYW1sMjpBdHRyaWJ1dGU+PC9zYW1sMjpBdHRyaWJ1dGVTdGF0ZW1lbnQ+PC9zYW1sMjpBc3NlcnRpb24+PC9zYW1sMnA6UmVzcG9uc2U+";
		public static void main(String[] args) throws Exception {
		 //Get Document Builder
	     
	      //Build Document
	      //Document document = builder.parse("<?xml version=\'1.0\' encoding=\'UTF-8\'?>\n<MessageComment reasoncode=\'161\'>test teh value</MessageComment>");

	    //  String s="<?xml version=\'1.0\' encoding=\'UTF-8\'?>\n<MessageComment reasoncode=\'161\'>test teh value</MessageComment>";
	   
	      //if(s!=null)
	    	
			String s="\'abc\'";
			
			
			s=s.replace("'", "\"");
			
			System.out.println(s);
	     // System.out.println("decodedddd"+decodeMessage(s));
	     
	}
	
	
	
	protected static String decodeMessage(String message) {
	    try {
	    	DefaultBootstrap.bootstrap();
	    	System.out.println(message);
	        byte[] decodedBytes = Base64.decode(message);
	      

	        InputStream is = new ByteArrayInputStream(decodedBytes);
	        
	        //InputStream is=new FileInputStream("d://usp//mani.xml");
	        
	      //  is = new InflaterInputStream(is);
	       /* FileOutputStream out=new FileOutputStream("d://usp//abc.xml");
	        byte[] buffer = new byte[1024];
	        int bytesRead;
	        System.out.println(is.read(buffer));
	        while ((bytesRead = is.read(buffer)) != -1) {
	        	out.write(buffer, 0, bytesRead);
	        }
	        out.flush();
	        out.close();*/

	       // System.out.println("nulllllll"+is);
	        
	        Document messageDoc = new BasicParserPool().parse(is);
	        Element messageElem = messageDoc.getDocumentElement();
	     
	        Unmarshaller unmarshaller = Configuration.getUnmarshallerFactory().getUnmarshaller(messageElem);
	        System.out.println("unmarshall data>>>>"+messageElem);

	        return  unmarshaller.unmarshall(messageElem).toString();
	    } catch (Exception e) {
	    	e.printStackTrace();
	        //
	    }
	    return null;
	}
	
	
	public static XMLObject unmarshall(String authReqStr) throws Exception {
	    try {
	     
	        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
	        documentBuilderFactory.setNamespaceAware(true);
	        DocumentBuilder docBuilder = documentBuilderFactory.newDocumentBuilder();
	        Document document = docBuilder.parse(new ByteArrayInputStream(authReqStr.trim().getBytes()));
	        Element element = document.getDocumentElement();
	      
	        UnmarshallerFactory unmarshallerFactory = Configuration.getUnmarshallerFactory();
	        Unmarshaller unmarshaller = unmarshallerFactory.getUnmarshaller(element);
	     System.out.println();
	        
	        return unmarshaller.unmarshall(element);
	    } catch (Exception e) {
	        throw new Exception("Error in constructing AuthRequest from " +
	                            "the encoded String ", e);
	    }
	    }
	

	private static String getReasonCode(String s)  {
		  DocumentBuilderFactory factory = null;
	      DocumentBuilder builder =null;

	      
		  InputSource is=new InputSource();
	      is.setCharacterStream(new StringReader(s));
	      Document document = null;
	      String reasonCode=null;;
	     
          try
          {
        	  factory=DocumentBuilderFactory.newInstance();
        	  builder = factory.newDocumentBuilder();
        	  document = builder.parse(is);
        	  document.getDocumentElement().normalize();
        	  Node root=document.getDocumentElement();
        	  System.out.println(root);
	    
	      NodeList nodeList = document.getElementsByTagName("MessageComment");
	      for (int temp = 0; temp < nodeList.getLength(); temp++)
	      {
	         Node node = nodeList.item(temp);
	         if (node.getNodeType() == Node.ELEMENT_NODE)
	         {
	        	 Element ele=(Element)node;
	        	 
	        	 System.out.println(ele);
	            if (node.hasAttributes()) {
	               NamedNodeMap nodeMap = node.getAttributes();
	               for (int i = 0; i < nodeMap.getLength(); i++)
	               {
	                   Node tempNode = nodeMap.item(i);
	                   if(tempNode.getNodeName().equals("reasoncode"))
	                   {
	                	   reasonCode= tempNode.getNodeValue();
	                	   break;
	                    }
	               }

	           }
	         }
	      }
          }
          catch(SAXException sae)
          {
        	  
          }
          catch( IOException ioe)
          {
        	  
          }
          catch(ParserConfigurationException e)
          {
        	  
          }
          finally {
        	   factory = null;
    	       builder =null;
    	       document = null;
    	    
			
		}
          return reasonCode;
	}
}
