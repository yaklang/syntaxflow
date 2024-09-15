<?php
//单一入口模式
error_reporting(0);
$file = addslashes($_GET['r']);
$action = $file == '' ? 'index' : $file;
include('files/' . $action . '.php');
?>