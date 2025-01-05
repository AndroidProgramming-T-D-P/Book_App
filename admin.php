<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Admin Dashboard</title>
    <script src="https://cdn.tailwindcss.com"></script>
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css" rel="stylesheet">
</head>
<body class="bg-gray-50">


<!-- Sidebar -->
<aside class="bg-gradient-to-b from-red-600 to-red-800 w-64 min-h-screen px-4 py-8 fixed">
    <div class="flex items-center justify-center mb-8">
        <img src="https://images.unsplash.com/photo-1533090161767-e6ffed986c88?ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=64&h=64" alt="Logo" class="rounded-full w-12 h-12">
        <h1 class="text-white font-bold ml-3">Admin Portal</h1>
    </div>
    <nav>
        <button onclick="showSection('dashboard-section')" class="flex items-center text-white py-3 px-4 rounded hover:bg-red-700 mb-2 w-full">
            <i class="fas fa-home mr-3"></i> Dashboard
        </button>
        <button onclick="showSection('users-section')" class="flex items-center text-white py-3 px-4 rounded hover:bg-red-700 mb-2 w-full">
            <i class="fas fa-users mr-3"></i> User Management
        </button>
        <button onclick="showSection('books-section')" class="flex items-center text-white py-3 px-4 rounded hover:bg-red-700 w-full">
            <i class="fas fa-book mr-3"></i> Book Management
        </button>
    </nav>
</aside>
<div class="ml-64">
    <header class="bg-white shadow px-6 py-4">
        <div class="flex items-center justify-between">
            <h2 id="section-title" class="text-xl font-semibold text-gray-800">Dashboard</h2>
            <div class="flex items-center">
                <img src="https://images.unsplash.com/photo-1472099645785-5658abf4ff4e?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=facearea&facepad=2&w=48&h=48" alt="Admin" class="w-8 h-8 rounded-full">
            </div>
        </div>
    </header>

          <!-- Dashboard Section -->
    <div id="dashboard-section" class="p-6">
        <div class="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-6 mb-6">
            <div class="bg-white rounded-lg shadow p-6">
                <div class="flex items-center">
                    <div class="p-3 rounded-full bg-red-100 text-red-600">
                        <i class="fas fa-users text-2xl"></i>
                    </div>
                    <div class="ml-4">
                        <h3 class="text-gray-500 text-sm">Total Users</h3>
                        <p class="text-2xl font-semibold text-gray-800" id="total-users">0</p>
                    </div>
                </div>
            </div>
            <div class="bg-white rounded-lg shadow p-6">
                <div class="flex items-center">
                    <div class="p-3 rounded-full bg-blue-100 text-blue-600">
                        <i class="fas fa-book text-2xl"></i>
                    </div>
                    <div class="ml-4">
                        <h3 class="text-gray-500 text-sm">Total Books</h3>
                        <p class="text-2xl font-semibold text-gray-800" id="total-books">0</p>
                    </div>
                </div>
            </div>
        </div>
    </div>

        <!-- User Management Section -->
        <div id="users-section" class="mx-auto max-w-7xl px-4 sm:px-6 lg:px-8 py-8">
            <div class="sm:flex sm:items-center">
                <div class="sm:flex-auto">
                    <h1 class="text-xl font-semibold text-gray-900">Users</h1>
                </div>
                <div class="mt-4 sm:ml-16 sm:mt-0 sm:flex-none">
                    <button onclick="openAddUserModal()" class="block rounded-md bg-indigo-600 px-3 py-2 text-center text-sm font-semibold text-white shadow-sm hover:bg-indigo-500">Add User</button>
                </div>
            </div>
            <div class="mt-8 flow-root">
                <div class="-mx-4 -my-2 overflow-x-auto sm:-mx-6 lg:-mx-8">
                    <div class="inline-block min-w-full py-2 align-middle sm:px-6 lg:px-8">
                        <table class="min-w-full divide-y divide-gray-300">
                            <thead>
                                <tr>
                                    <th class="py-3.5 pl-4 pr-3 text-left text-sm font-semibold text-gray-900">User ID</th>
                                    <th class="px-3 py-3.5 text-left text-sm font-semibold text-gray-900">Email</th>
                                    <th class="px-3 py-3.5 text-left text-sm font-semibold text-gray-900">Username</th>
                                    <th class="px-3 py-3.5 text-left text-sm font-semibold text-gray-900">Created Date</th>
                                    <th class="px-3 py-3.5 text-left text-sm font-semibold text-gray-900">Permissions</th>
                                    <th class="relative py-3.5 pl-3 pr-4 sm:pr-0">Actions</th>
                                </tr>
                            </thead>
                            <tbody id="users-table" class="divide-y divide-gray-200"></tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>

    <!-- Book Management Section -->
<div id="books-section" class="mx-auto max-w-7xl px-4 sm:px-6 lg:px-8 py-8">
    <div class="sm:flex sm:items-center">
        <div class="sm:flex-auto">
            <h1 class="text-xl font-semibold text-gray-900">Books</h1>
        </div>
        <div class="mt-4 sm:ml-16 sm:mt-0 sm:flex-none">
            <button onclick="openAddBookModal()" class="block rounded-md bg-indigo-600 px-3 py-2 text-center text-sm font-semibold text-white shadow-sm hover:bg-indigo-500">Add Book</button>
        </div>
    </div>
    <div class="mt-8">
        <div id="books-grid" class="grid grid-cols-1 md:grid-cols-3 lg:grid-cols-4 gap-6">
            <!-- Books will be dynamically added here -->
        </div>
    </div>
</div>

<!-- Book Modal -->
<div id="bookModal" class="hidden fixed inset-0 bg-gray-500 bg-opacity-50 flex justify-center items-center">
    <form id="bookForm" class="bg-white p-6 rounded shadow-md space-y-4">
        <h2 class="text-lg font-semibold text-gray-800">Add Book</h2>
        <div>
            <label class="block text-sm font-medium text-gray-700">Title</label>
            <input id="bookTitle" type="text" class="block w-full mt-1 border-gray-300 rounded-md shadow-sm">
        </div>
        <div>
            <label class="block text-sm font-medium text-gray-700">Author</label>
            <input id="bookAuthor" type="text" class="block w-full mt-1 border-gray-300 rounded-md shadow-sm">
        </div>
        <div>
            <label class="block text-sm font-medium text-gray-700">Category ID</label>
            <input id="bookCategory" type="number" class="block w-full mt-1 border-gray-300 rounded-md shadow-sm">
        </div>
        <div>
            <label class="block text-sm font-medium text-gray-700">Published Date</label>
            <input id="publishedDate" type="date" class="block w-full mt-1 border-gray-300 rounded-md shadow-sm">
        </div>
        <div>
            <label class="block text-sm font-medium text-gray-700">Cover Image</label>
            <input id="bookCover" type="text" class="block w-full mt-1 border-gray-300 rounded-md shadow-sm">
        </div>
        <div>
            <label class="block text-sm font-medium text-gray-700">File Path</label>
            <input id="bookFilePath" type="text" class="block w-full mt-1 border-gray-300 rounded-md shadow-sm">
        </div>
        <div>
            <label class="block text-sm font-medium text-gray-700">Audiobook File Path</label>
            <input id="audiobookFilePath" type="text" class="block w-full mt-1 border-gray-300 rounded-md shadow-sm">
        </div>
        <div>
            <label class="block text-sm font-medium text-gray-700">Duration (mins)</label>
            <input id="duration" type="number" class="block w-full mt-1 border-gray-300 rounded-md shadow-sm">
        </div>
        <div>
            <label class="block text-sm font-medium text-gray-700">Is Free</label>
            <select id="isFree" class="block w-full mt-1 border-gray-300 rounded-md shadow-sm">
                <option value="1">Yes</option>
                <option value="0">No</option>
            </select>
        </div>
        <div>
            <label class="block text-sm font-medium text-gray-700">Is Featured</label>
            <select id="isFeatured" class="block w-full mt-1 border-gray-300 rounded-md shadow-sm">
                <option value="1">Yes</option>
                <option value="0">No</option>
            </select>
        </div>
        <div>
            <label class="block text-sm font-medium text-gray-700">Rating</label>
            <input id="rating" type="number" step="0.1" max="5" class="block w-full mt-1 border-gray-300 rounded-md shadow-sm">
        </div>
        <button type="button" onclick="saveBook()" class="w-full bg-indigo-600 text-white px-4 py-2 rounded-md shadow hover:bg-indigo-500">Save</button>
        <button type="button" onclick="closeModal('bookModal')" class="w-full bg-gray-300 text-gray-800 px-4 py-2 rounded-md shadow">Cancel</button>
    </form>
</div>


        <!-- Modals -->
        <div id="userModal" class="hidden fixed inset-0 bg-gray-500 bg-opacity-50 flex justify-center items-center">
            <form id="userForm" class="bg-white p-6 rounded shadow-md space-y-4">
                <h2 class="text-lg font-semibold text-gray-800">Add User</h2>
                <div>
                    <label class="block text-sm font-medium text-gray-700">Email</label>
                    <input id="userEmail" type="email" class="block w-full mt-1 border-gray-300 rounded-md shadow-sm">
                </div>
                <div>
                    <label class="block text-sm font-medium text-gray-700">Username</label>
                    <input id="userName" type="text" class="block w-full mt-1 border-gray-300 rounded-md shadow-sm">
                </div>
                <div>
                    <label class="block text-sm font-medium text-gray-700">Password</label>
                    <input id="userPassword" type="password" class="block w-full mt-1 border-gray-300 rounded-md shadow-sm">
                </div>
                <button type="button" onclick="saveUser()" class="w-full bg-indigo-600 text-white px-4 py-2 rounded-md shadow hover:bg-indigo-500">Save</button>
                <button type="button" onclick="closeModal('userModal')" class="w-full bg-gray-300 text-gray-800 px-4 py-2 rounded-md shadow">Cancel</button>
            </form>
        </div>
    </div>


    <script>
        async function fetchUsers() {
            const res = await fetch('nguoidung.php?action=list');
            const users = await res.json();
            const tbody = document.getElementById('users-table');
            const dashboardBooks = document.getElementById('dashboard-books');
            tbody.innerHTML = users.map(user => `
                <tr>
                    <td>${user.user_id}</td>
                    <td>${user.email}</td>
                    <td>${user.userName}</td>
                    <td>${user.ngayTao}</td>
                    <td>${user.quyen == 1 ? 'Admin' : 'User'}</td>
                    <td>
                        <button onclick="deleteUser(${user.user_id})" class="text-red-600"><i class="fas fa-trash"></i></button>
                    </td>
                </tr>
            `).join('');
        }

        async function deleteUser(id) {
            await fetch('nguoidung.php?action=delete', {
                method: 'POST',
                body: new URLSearchParams({ id })
            });
            fetchUsers();
        }

        async function saveUser() {
            const email = document.getElementById('userEmail').value;
            const userName = document.getElementById('userName').value;
            const password = document.getElementById('userPassword').value;

            await fetch('nguoidung.php?action=add', {
                method: 'POST',
                body: new URLSearchParams({
                    email,
                    userName,
                    userPassWord: password
                })
            });
            closeModal('userModal');
            fetchUsers();
        }

        function openAddUserModal() {
            document.getElementById('userModal').classList.remove('hidden');
        }

        function closeModal(id) {
            document.getElementById(id).classList.add('hidden');
        }

        
        fetchUsers();

        async function fetchBooks() {
    try {
        const res = await fetch('books.php?action=list');
        if (!res.ok) throw new Error('Failed to fetch books');
        const books = await res.json();

        const grid = document.getElementById('books-grid');
        grid.innerHTML = books.map(book => `
            <div class="bg-white rounded-lg shadow-md overflow-hidden">
                <img src="${book.cover_image}" alt="${book.title}" class="w-full h-48 object-cover">
                <div class="p-4 space-y-2">
                    <h3 class="text-lg font-medium text-gray-900">${book.title}</h3>
                    <p class="text-sm text-gray-600">Author: ${book.author}</p>
                    <p class="text-sm text-gray-600">Category: ${book.category_id}</p>
                    <p class="text-sm text-gray-600">Published Date: ${book.published_date || 'N/A'}</p>
                    <p class="text-sm text-gray-600">Duration: ${book.duration ? book.duration + ' mins' : 'N/A'}</p>
                    <p class="text-sm text-gray-600">Rating: ${book.rating ? book.rating + '/5' : 'N/A'}</p>
                    <p class="text-sm text-gray-600">Free: ${book.is_free ? 'Yes' : 'No'}</p>
                    <p class="text-sm text-gray-600">Featured: ${book.is_featured ? 'Yes' : 'No'}</p>
                    <div class="mt-3 flex space-x-2 justify-end">
                        <button onclick="editBook(${book.book_id})" class="text-blue-600 hover:text-blue-900">
                            <i class="fas fa-edit"></i>
                        </button>
                        <button onclick="deleteBook(${book.book_id})" class="text-red-600 hover:text-red-900">
                            <i class="fas fa-trash"></i>
                        </button>
                    </div>
                </div>
            </div>
        `).join('');
    } catch (error) {
        console.error(error);
    }
}


async function saveBook() {
    const title = document.getElementById('bookTitle').value;
    const author = document.getElementById('bookAuthor').value;
    const category_id = document.getElementById('bookCategory').value;
    const cover_image = document.getElementById('bookCover').value;
    const published_date = document.getElementById('publishedDate').value;
    const file_path = document.getElementById('bookFilePath').value;
    const audiobook_file_path = document.getElementById('audiobookFilePath').value;
    const duration = document.getElementById('duration').value;
    const is_free = document.getElementById('isFree').value;
    const is_featured = document.getElementById('isFeatured').value;
    const rating = document.getElementById('rating').value;

    try {
        const res = await fetch('books.php?action=add', {
            method: 'POST',
            body: new URLSearchParams({
                title,
                author,
                category_id,
                cover_image,
                published_date,
                file_path,
                audiobook_file_path,
                duration,
                is_free,
                is_featured,
                rating
            })
        });

        if (res.ok) {
            alert('Book added successfully!');
            closeModal('bookModal');
            fetchBooks();
        } else {
            throw new Error('Failed to add book');
        }
    } catch (error) {
        alert(error.message);
    }
}


async function deleteBook(bookId) {
    const res = await fetch('books.php?action=delete', {
        method: 'POST',
        body: new URLSearchParams({ book_id: bookId })
    });

    if (res.ok) {
        fetchBooks();
    } else {
        console.error('Failed to delete book');
    }
}

function openAddBookModal() {
    document.getElementById('bookModal').classList.remove('hidden');
}

function closeModal(id) {
    document.getElementById(id).classList.add('hidden');
}


fetchBooks();

    </script>

<script>
    function showSection(sectionId) {
    
    document.querySelectorAll('div[id$="-section"]').forEach(section => {
        section.classList.add('hidden');
    });

    
    document.getElementById(sectionId).classList.remove('hidden');

    
    const sectionTitles = {
        'dashboard-section': 'Dashboard',
        'users-section': 'User Management',
        'books-section': 'Book Management'
    };
    document.getElementById('section-title').innerText = sectionTitles[sectionId];

}


    async function fetchCounts() {
       
        const usersRes = await fetch('nguoidung.php?action=list');
        const users = await usersRes.json();
        document.getElementById('total-users').innerText = users.length;

        const booksRes = await fetch('books.php?action=list');
        const books = await booksRes.json();
        document.getElementById('total-books').innerText = books.length;
    }

   
    fetchCounts();
    fetchUsers();
    fetchBooks();
</script>
    
</body>
</html>
