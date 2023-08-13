<?php 
//Conexion con la base de datos y el servidor

$link = mysql_connect("localhost","root","") or die ("<h2> No se encuentra el servidor </h2>");
$db = mysql_select_db("GuardarLibro",$link) or die ("<h2> Error de conexion </h2>");


// Obtenemos los valores del formulario

$nombreLibro   = $_POST['date'];
$numeroPaginas = $_POST['number1'];
$nombreAutor   = $_POST['nombre1'];
$numeroIsbn    = $_POST['number'];

// Ingresar la informacion a la tabla DatosLibros

mysql_query("INSERT INTO DatosLibros VALUES('','$nombreLibro','$numeroPaginas','$numeroIsbn','$nombreAutor)",$link)
or die ("<h2> Error de envio</h2>");

?>
