<%@tag description="templatebásico" pageEncoding="UTF-8"%>
<%@attribute name="body" fragment="true"%>
<%@attribute name="title"%>
<html>
	<head>
		<title>${title}</title>
		<script src="js/jquery.min.js"></script>
		<link rel="stylesheet" href="css/bootstrap.min.css">
		<link rel="stylesheet" href="css/fatec.css">
		<script src="js/bootstrap.min.js"></script>
	</head>
	<body>
		<div class="fatec container">
			<div id="body">
				<jsp:invoke fragment="body"/>
			</div>
		</div>
	</body>
</html>