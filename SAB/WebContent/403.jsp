<!DOCTYPE unspecified PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
	<head>
	
	<title>Acceso Denegado</title>
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/rime/rime.css" />
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/sab_estilos.css" />
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
					<br/>
					<br/>
					<br/>
					<br/>
					<br/>
					<br/>
					<label class="titulo">Acceso Denegado</label>
					<br/>
					<br/>
					<br/>
					<label>Usted está intentando ingresar a una página a la que no posee permisos.</label>
					<br/>
					<label>Por favor contacte al administrador.</label>
					
					<br/>
					<br/>
					<table width="30%">
						<tr>
							<!-- <td width="50%" align="center">
								<h2><a href="<%=request.getContextPath() + "/index.jspx"%>">Inicio</a></h2>
							</td> -->
							<td width="50%" align="center">
								<h2><a href="javascript:history.back();">Volver</a></h2>
							</td>
						</tr>
					</table>
				</div>
				<br/>
			</fieldset>
		</div>
	</body>
</html>
