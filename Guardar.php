<?php 
//Conexion con la base de datos y el servidor

$server = "localhost";
$user = "root";
$pass = "";
$db = "librosalmacenados";

$conexion = new mysqli($server, $user, $pass, $db);



if($conexion->connect_errno){
    die("conexion fallida" . $conexion->connect_errno);
} else{
    echo " conectado";
}

//asta esta parte esta joya me da la conexion a la base de datos //

// Obtenemos los valores del formulario

$nombreLibro   = $_POST['date'];
$numeroPaginas = $_POST['number1'];
$nombreAutor   = $_POST['nombre1'];
$numeroIsbn    = $_POST['number'];

// Ingresar la informacion a la tabla DatosLibros

mysql_query("INSERT INTO DatosLibros VALUES('','$nombreLibro','$numeroPaginas','$numeroIsbn','$nombreAutor)",$link)
or die ("<h2> Error de envio</h2>");


?>
