<%@include file="inc_verifica_acesso_usuario.jsp"%>
<%@ page contentType="text/html" language="java" import="java.sql.*, java.util.*, model.Leito"%>
<jsp:useBean id="leito" class="model.Leito"/>
<%
	String pStringCoLeito = request.getParameter("co_leito");	
	int coLeito = Integer.parseInt(pStringCoLeito);
	String pStringCoStatusLeitoAtual = request.getParameter("co_status_leito");	
	int coStatusLeito = Integer.parseInt(pStringCoStatusLeitoAtual);
	Leito l = leito.getStatusLeito(coLeito);
	if (coStatusLeito != l.getCodigoStatusLeito()){
	String tbClass = "";
	switch (l.getCodigoStatusLeito()){
		case 1: 
			tbClass = "tbbloqueado";
			break;
		case 2: 
			tbClass = "tbemhigienizacao";
			break;
		case 3: 
			tbClass = "tblivre";
			break;
		case 4: 
			tbClass = "tbocupado";
			break;
	}
%>
<input type="hidden" id="co_leito_status_<%=coLeito %>" name="co_leito_status_<%=coLeito %>" value="<%=l.getCodigoStatusLeito() %>">
<table class="<%=tbClass%>">
	<thead>
		<tr>
			<th colspan="3">Leito: <%=l.getCodigoLeito() %></th>
		</tr>
	</thead>
	<tbody>
		<tr>
			<td align="right">Status:</td>
			<td align="left"><font style="font-weight: bold; font-size: 11px;"><%=l.getDescricaoStatusLeito() %></font></td>
			<td align="right"><button onclick="liberarLeito(<%=l.getCodigoLeito() %>, <%=l.getCodigoStatusLeito() %>)" <%if ((l.getCodigoStatusLeito() != 2) || (!(session.getAttribute("co_tipo_usuario").equals(1)))){ %>disabled<%} %>><img src="_imagens/icones/16X16/apply.gif"><font style="font-weight: bold; font-size: 11px;">LIBERAR</font></button></td>
		</tr>
		<tr>
			<td align="right" colspan="3"><button onclick="desbloquearLeito(<%=l.getCodigoLeito() %>, <%=l.getCodigoStatusLeito() %>)" <%if ((l.getCodigoStatusLeito() != 1) || (!(session.getAttribute("co_tipo_usuario").equals(2)))){ %>disabled<%} %>><img src="_imagens/icones/16X16/unlock.gif"><font style="font-weight: bold; font-size: 11px;">DESBLOQUEAR</font></button>&nbsp;<button onclick="bloquearLeito(<%=l.getCodigoLeito() %>, <%=l.getCodigoStatusLeito() %>)" <%if ((l.getCodigoStatusLeito() == 1) || (l.getCodigoStatusLeito() == 4) || (!(session.getAttribute("co_tipo_usuario").equals(2)))){ %>disabled<%} %>><img src="_imagens/icones/16X16/lock.gif"><font style="font-weight: bold; font-size: 11px;">BLOQUEAR</font></button></td>
		</tr>
	</tbody>
</table>
<%
	}
	else{
%>
0
<%
	}
%>
