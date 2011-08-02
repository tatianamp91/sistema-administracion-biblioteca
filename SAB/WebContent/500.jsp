<%@ page isErrorPage="true"%>
<!DOCTYPE unspecified PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
	<head>
	
	<title>Error Desconocido</title>
	<link rel="stylesheet" type="text/css"
		href="<%=request.getContextPath()%>/css/rime/rime.css" />
	<link rel="stylesheet" type="text/css"
		href="<%=request.getContextPath()%>/css/sab_estilos.css" />
	</head>
	<body>
		<div id="logo" class="fondoHeader" xmlns:ui="http://java.sun.com/jsf/facelets" align="center">
			<fieldset style="width: 81%;">
				<div class="background">
					<img src="<%=request.getContextPath()%>/images/Unicatolica2.png" style="z-index:1" class="background"/>
				</div>
			</fieldset>
			<fieldset style="height: 415px; width: 81%;">
				<div align="center"><br />
					<label class="titulo">Error Desconocido</label>
					<br/>
					<br/>
					<label>Se ha generado un error de aplicación no esperado, por favor reintente la operación.</label>
					<br/>
					<textarea name="textarea" cols="140" rows="20" readonly="readonly" style="text-align:left;">
					<%
						if (exception != null) {
									exception.printStackTrace(new java.io.PrintWriter(out));
								}
					%>
					</textarea>
					<h2><a href="javascript:history.back();">Volver</a></h2>
				</div>
				<br/>
			</fieldset>
		</div>
	</body>
</html>
