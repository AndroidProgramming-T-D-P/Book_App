<?php
include 'connect.php';

$action = $_GET['action'] ?? '';

if ($action === 'list') {
    // Lấy danh sách sách
    $result = $conn->query("SELECT * FROM books");
    if (!$result) {
        echo json_encode(['error' => 'Failed to fetch books']);
        exit;
    }
    $books = [];
    while ($row = $result->fetch_assoc()) {
        $books[] = $row;
    }
    echo json_encode($books);

} elseif ($action === 'add') {
    // Thêm sách mới
    $title = $_POST['title'] ?? '';
    $author = $_POST['author'] ?? '';
    $category_id = (int)($_POST['category_id'] ?? 0);
    $published_date = $_POST['published_date'] ?? null;
    $cover_image = $_POST['cover_image'] ?? '';
    $file_path = $_POST['file_path'] ?? '';
    $audiobook_file_path = $_POST['audiobook_file_path'] ?? '';
    $duration = (int)($_POST['duration'] ?? 0);
    $is_free = (int)($_POST['is_free'] ?? 0);
    $is_featured = (int)($_POST['is_featured'] ?? 0);
    $rating = (float)($_POST['rating'] ?? 0);

    $stmt = $conn->prepare("INSERT INTO books 
        (title, author, category_id, published_date, cover_image, file_path, audiobook_file_path, duration, is_free, is_featured, rating) 
        VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
    $stmt->bind_param("ssissssiiii", $title, $author, $category_id, $published_date, $cover_image, $file_path, $audiobook_file_path, $duration, $is_free, $is_featured, $rating);

    if ($stmt->execute()) {
        echo json_encode(['status' => 'success', 'book_id' => $stmt->insert_id]);
    } else {
        echo json_encode(['error' => 'Failed to add book', 'details' => $stmt->error]);
    }
    $stmt->close();

} elseif ($action === 'delete') {
    // Xóa sách
    $book_id = (int)($_POST['book_id'] ?? 0);
    $stmt = $conn->prepare("DELETE FROM books WHERE book_id = ?");
    $stmt->bind_param("i", $book_id);

    if ($stmt->execute()) {
        echo json_encode(['status' => 'success']);
    } else {
        echo json_encode(['error' => 'Failed to delete book', 'details' => $stmt->error]);
    }
    $stmt->close();

} elseif ($action === 'update') {
    // Cập nhật sách
    $book_id = (int)($_POST['book_id'] ?? 0);
    $title = $_POST['title'] ?? '';
    $author = $_POST['author'] ?? '';
    $category_id = (int)($_POST['category_id'] ?? 0);
    $published_date = date('Y-m-d');
    $cover_image = $_POST['cover_image'] ?? '';
    $file_path = $_POST['file_path'] ?? '';
    $audiobook_file_path = $_POST['audiobook_file_path'] ?? '';
    $duration = (int)($_POST['duration'] ?? 0);
    $is_free = (int)($_POST['is_free'] ?? 0);
    $is_featured = (int)($_POST['is_featured'] ?? 0);
    $rating = (float)($_POST['rating'] ?? 0);

    $stmt = $conn->prepare("UPDATE books 
        SET title = ?, author = ?, category_id = ?, published_date = ?, cover_image = ?, file_path = ?, audiobook_file_path = ?, duration = ?, is_free = ?, is_featured = ?, rating = ? 
        WHERE book_id = ?");
    $stmt->bind_param("ssissssiiiii", $title, $author, $category_id, $published_date, $cover_image, $file_path, $audiobook_file_path, $duration, $is_free, $is_featured, $rating, $book_id);

    if ($stmt->execute()) {
        echo json_encode(['status' => 'success']);
    } else {
        echo json_encode(['error' => 'Failed to update book', 'details' => $stmt->error]);
    }
    $stmt->close();

} else {
    echo json_encode(['error' => 'Invalid action']);
}

$conn->close();
?>
