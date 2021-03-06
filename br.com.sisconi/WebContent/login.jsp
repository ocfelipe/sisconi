<%@ page language="java" contentType="text/html; charset=windows-1252"
    pageEncoding="windows-1252"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%@include file="inc_head.jsp"%>
<script type="text/javascript">

$(function(){
	$("#nr_cpf").focus();
});

var pMsg = <%=request.getParameter("msg")%>

function mostrarMsg(){
	if (pMsg != '' && pMsg != null){
		alert(pMsg);
	}
}	

function obterTipoUsuario(nr_cpf){
	$.ajax({
  		url: "ajax_obter_tipo_usuario.jsp?nrCpf=" + nr_cpf,
  		beforeSend: function () {
    		$("#spantipousuario").html("");
  		}
	}).done(function (retornoSucesso) {
    		$("#spantipousuario").html(retornoSucesso);
	});
}

function onBlurCpf(pCpf){
	obterTipoUsuario(pCpf);
	ValidarCPF(document.getElementById('nr_cpf').value, 'nr_cpf');
}
</script>
<body onload="mostrarMsg()">
<table class="tblConteudo">
<tr>
	<td class="tblConteudoTitulo">	
		<table border="0" cellpadding="0" cellspacing="0" width="100%">
		<tr>
			<td>
				<img border="0" width="100px" height="98px" src="_imagens/logos/logo_sisconi.jpg">
			</td>
			<td valign="bottom" align="right">
				<form id="frm_login" action="login_processa.jsp" method="post">
				<table border="0" cellpadding="0" cellspacing="10">
					<tr>
						<td align="right" valign="middle">CPF:</td>
						<td align="left" valign="middle"><input type="text" id="nr_cpf" name="nr_cpf" onBlur="onBlurCpf(this.value);" onKeyPress="MascaraCPF(form.nr_cpf);" maxlength="14" size="20" placeholder="Insira o CPF" required></td>
					</tr>
					<tr>
						<td align="right" valign="middle">Senha:</td>
						<td align="left" valign="middle"><input type="password" id="ds_senha" name="ds_senha" maxlength="16" size="20" placeholder="Insira a senha" required></td>
					</tr>
					<tr>
						<td align="right" valign="middle">Tipo:</td>
						<td align="left" valign="middle"><span id="spantipousuario">-</span></td>
					</tr>
					<tr>
						<td></td>
						<td align="right" valign="middle"><input type="submit" id="bt_entrar" name="bt_entrar" value="Entrar"></td>
					</tr>
				</table>
				</form>
			</td>
		</tr>
		</table>
	</td>
</tr>
<tr>
	<td class="tblConteudoCorpo" align="center"><hr>Bem Vindo ao Sistema de Controle de Internação do Hospital Universitário - SISCONI</td>
</tr>
<tr>
	<td class="tblConteudoRodape"><hr><%@include file="inc_rodape.jsp"%></td>
</tr>
</table>
</body>
</html>