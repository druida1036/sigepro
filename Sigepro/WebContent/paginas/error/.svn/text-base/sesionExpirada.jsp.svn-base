<%@page
	import="co.com.sigepro.control.util.PropiedadesUtils,co.com.sigepro.control.util.Constantes"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
   "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<title><%=PropiedadesUtils.getMensaje(Constantes.ARCHIVO_MENSAJES,
					"jsp.aplicacion.titulo")%></title>
<link rel="shortcut icon"
	href="<%=request.getContextPath() + "/favicon.ico"%>" />
<link rel="stylesheet"
	href="<%=request.getContextPath() + "/tema/css/tema.css"%>"
	type="text/css" />
</head>
<body>
<div id="body_wrapper" class="central">
<div class="menuButtons">
<table width="100%" border="0" cellspacing="0" cellpadding="0">
	<tr>
		<td class="mastHead">
		<div id="logo" class="cabezoteautenticado"></div>

		<div class="topLinks">
		<div class="topLinkUni"><span class="topLinkSep"><img
			alt="pixel"
			src="<%=request.getContextPath() + "/tema/imagen/aig_pixel.gif"%>" /></span></div>
		</div>
		</td>
	</tr>
</table>
</div>
<div class="AccesoDenegado">
<div class="rich-panel-header"
	style="height: 60px; background-repeat: no-repeat; padding-left: 10px;">
<%=PropiedadesUtils.getMensaje(Constantes.ARCHIVO_MENSAJES,
					"jsp.sesionExpirada.sesionExpirada")%></div>
<p><a
	href="<%=request.getContextPath()
					+ "/faces/paginas/login/login.xhtml"%>">
<%=PropiedadesUtils.getMensaje(Constantes.ARCHIVO_MENSAJES,
					"jsp.sesionExpirada.iniciarSesion")%> </a></p>
</div>
</div>
<div class="footer" style="text-align: center;">
<p><%=PropiedadesUtils.getMensaje(Constantes.ARCHIVO_MENSAJES,
					"jsp.footer.derechos")%>. Privacidad y Condiciones</p>
</div>
</body>
</html>
