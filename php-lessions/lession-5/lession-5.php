<?php

class SinkController extends Controller
{
    public $a;

    public function TestExec()
    {
        $this->a = input("a");
        $a = "include/test/upload/";
        $path = $this->a ? "default/" : $this->a;
        if (file_exists($path)) {
            exec("ffmpeg.exe - i " . $path . "-af ");
        } else {
            exit("path exit");
        }
    }
}