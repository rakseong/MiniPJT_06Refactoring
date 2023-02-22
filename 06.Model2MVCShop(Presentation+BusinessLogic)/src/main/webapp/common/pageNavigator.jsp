<%@ page contentType="text/html; charset=euc-kr" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

	<fmt:parseNumber var="csp" integerOnly="true" value="${(resultPage.currentPage mod resultPage.pageUnit)==0 ? (resultPage.currentPage div resultPage.pageUnit)-1 : (resultPage.currentPage div resultPage.pageUnit)}"/>
	<c:if test="${resultPage.currentPage > resultPage.pageUnit}">
		<a href="javascript:fncGetList('${csp*resultPage.pageUnit}')"><< </a>
	</c:if>
	<c:forEach var="i" begin="${resultPage.beginUnitPage}" end ="${resultPage.endUnitPage}">
		<a href="javascript:fncGetList('${i}')">${i}</a>
	</c:forEach>
	<c:if test="${resultPage.endUnitPage < resultPage.maxPage}">
		<a href="javascript:fncGetList('${(csp+1)*resultPage.pageUnit+1}')">>> </a>
	</c:if>