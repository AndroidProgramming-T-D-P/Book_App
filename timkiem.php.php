
<?php
include "connect.php";

$title = isset($_POST['title']) ? trim($_POST['title']) : '';

// Kiểm tra nếu title không rỗng
if (!empty($title)) {
    // Chuẩn bị câu truy vấn với Prepared Statements để tránh SQL Injection
    $query = "SELECT * FROM `books` WHERE `title` LIKE ?";
    $stmt = mysqli_prepare($conn, $query);

    // Thêm dấu '%' vào trước và sau từ khóa tìm kiếm
    $searchTerm = "%" . $title . "%";
    mysqli_stmt_bind_param($stmt, "s", $searchTerm); // 's' là kiểu dữ liệu chuỗi (string)

    // Thực thi câu truy vấn
    mysqli_stmt_execute($stmt);

    // Lấy kết quả
    $result = mysqli_stmt_get_result($stmt);
    $books = array();

    // Duyệt qua kết quả và lưu vào mảng
    while ($row = mysqli_fetch_assoc($result)) {
        $books[] = $row;
    }

    // Kiểm tra kết quả và trả về thông báo
    if (!empty($books)) {
        $arr = [
            'success' => true,
            'message' => "Tìm thấy kết quả",
            'result' => $books
        ];
    } else {
        $arr = [
            'success' => false,
            'message' => "Không tìm thấy kết quả",
            'result' => []
        ];
    }

    // In ra kết quả dưới dạng JSON
    echo json_encode($arr);
} else {
    // Nếu không có title, trả về thông báo lỗi
    $arr = [
        'success' => false,
        'message' => "Vui lòng nhập từ khóa tìm kiếm",
        'result' => []
    ];

    echo json_encode($arr);
}
?>
​
