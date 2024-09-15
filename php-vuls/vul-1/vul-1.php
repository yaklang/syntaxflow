<?php

$filepath = $this->frparam('filepath', 1);
if (strpos($filepath, '.') !== false) {
    JsonReturn(array('code' => 1, 'msg' => '参数存在安全隐患！'));
}
$action = $this->frparam('action', 1);
// 自己获取这些信息
$remote_url = urldecode($this->frparam('download_url', 1));
$remote_url = strpos($remote_url, '?') !== false ? $remote_url . '&version=' . $this->webconf['web_version'] : $remote_url . '?version=' . $this->webconf['web_version'];
$file_size = $this->frparam('filesize', 1);
$tmp_path = Cache_Path . "/update_" . $filepath . ".zip";//临时下载文件路径
switch ($action) {
    case 'prepare-download':
        $code = 0;
        ob_start();
        $ch = curl_init($remote_url);
        curl_setopt($ch, CURLOPT_HEADER, 1);
        curl_setopt($ch, CURLOPT_NOBODY, 1);
        $okay = curl_exec($ch);
        curl_close($ch);
        $head = ob_get_contents();
        ob_end_clean();
        $regex = '/Content-Length:\s([0-9].+?)\s/';
        $count = preg_match($regex, $head, $matches);
        $filesize = isset($matches[1]) && is_numeric($matches[1]) ? $matches[1] : 0;

        JsonReturn(array('code' => 0, 'size' => $filesize));
        break;
    case 'start-download':
        // 这里检测下 tmp_path 是否存在
        try {
            set_time_limit(0);
            touch($tmp_path);
            // 做些日志处理
            if ($fp = fopen($remote_url, "rb")) {
                if (!$download_fp = fopen($tmp_path, "wb")) {
                    exit;
                }
                while (!feof($fp)) {
                    if (!file_exists($tmp_path)) {
                        // 如果临时文件被删除就取消下载
                        fclose($download_fp);
                        exit;
                    }
                    fwrite($download_fp, fread($fp, 1024 * 8), 1024 * 8);
                }
                fclose($download_fp);
                fclose($fp);
            } else {
                exit;
            }
        } catch (Exception $e) {
            Storage::remove($tmp_path);
            JsonReturn(['code' => 1, 'msg' => '发生错误：' . $e->getMessage()]);
        }

        JsonReturn(['code' => 0, 'tmp_path' => $tmp_path]);
        break;
}