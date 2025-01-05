<?php
include "connect.php";
$email = $_POST['email'];
$pass = $_POST['userPassWord'];
$userName = $_POST['userName'];

//check data
$query = 'SELECT * FROM `nguoidung` WHERE `email` = "'.$email.'"';
$data = mysqli_query($conn, $query);
$numrow = mysqli_num_rows($data);

if($numrow > 0) {
    $arr = [
        'success' => false,
        'message' => "Email đã tồn tại"
    ];
} else {
    //insert
    $query = 'INSERT INTO `nguoidung`(`userName`, `userPassWord`, `email`) VALUES ("'.$userName.'","'.$pass.'","'.$email.'")';
    $data = mysqli_query($conn, $query);

    if($data == true) {
        $arr = [
            'success' => true,
            'message' => "Đăng ký thành công"
        ];
    } else {
        $arr = [
            'success' => false,
            'message' => "Không thành công"
        ];
    }
}


print_r(json_encode($arr));
?>
