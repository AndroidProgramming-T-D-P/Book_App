<?php
include 'connect.php';

$action = $_GET['action'] ?? '';

if ($action === 'list') {
    $result = $conn->query("SELECT * FROM nguoidung");
    $users = [];
    while ($row = $result->fetch_assoc()) {
        $users[] = $row;
    }
    echo json_encode($users);
} elseif ($action === 'add') {
    $email = $_POST['email'];
    $username = $_POST['userName'];
    $password = $_POST['userPassWord']; // Mật khẩu không mã hóa
    $quyen = $_POST['quyen'];

    $sql = "INSERT INTO nguoidung (email, userName, userPassWord, ngayTao, quyen) 
            VALUES ('$email', '$username', '$password', NOW(), '$quyen')";
    $conn->query($sql);
    echo json_encode(['status' => 'success']);
} elseif ($action === 'delete') {
    $id = $_POST['id'];
    $conn->query("DELETE FROM nguoidung WHERE user_id = $id");
    echo json_encode(['status' => 'success']);
} elseif ($action === 'update') {
    $id = $_POST['user_id'];
    $email = $_POST['email'];
    $username = $_POST['userName'];
    $quyen = $_POST['quyen'];

    $sql = "UPDATE nguoidung SET email = '$email', userName = '$username', quyen = '$quyen' WHERE user_id = $id";
    $conn->query($sql);
    echo json_encode(['status' => 'success']);
}

$conn->close();
?>
