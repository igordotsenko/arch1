<!DOCTYPE html>
<html>
<head>
    <title>Welcome to the Worst News EVER!</title>
</head>
<body>
	<h1>News</h1>
	<hr>
	<bl>
	<#list categories as category>
		<h3>${category.name}</h3>
			<ul>
				<#list category.articles as article>
      				<li>${article.title}</li>
				</#list>
			</ul>
	</#list>
</body>
</html>
