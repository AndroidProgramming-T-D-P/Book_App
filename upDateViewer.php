<?php
include "connect.php";

$book_id = $_POST['book_id'];

$query = "UPDATE `books` SET `view`= `view` + 1 WHERE `book_id` = $book_id";
$data = mysqli_query($conn, $query);

if ($data) {
    // Kiểm tra xem dòng nào đã được cập nhật
    if (mysqli_affected_rows($conn) > 0) {
        echo json_encode([
            'success' => true,
            'message' => 'View count updated successfully!',
            'result' => true
        ]);
    } else {
        echo json_encode([
            'success' => false,
            'message' => 'Book ID not found or no changes made.',
            'result' => true
        ]);
    }
} else {
    // Truy vấn lỗi
    echo json_encode([
        'success' => false,
        'message' => 'Failed to execute query.',
        'result' => false
    ]);
}

?>
