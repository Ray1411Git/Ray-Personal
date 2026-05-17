// ===============================
// Estado inicial
// ===============================
let todos = JSON.parse(localStorage.getItem("todos")) || [];
let filtroActual = "todas"; // "todas" | "activas" | "completadas"

// Guardar en localStorage
function guardar() {
    localStorage.setItem("todos", JSON.stringify(todos));
}

// ===============================
// Funciones de estado
// ===============================
function addTodo(text) {
    todos.push({ text: text, completed: false });
    guardar();
    renderTodos();
}

function toggleTodo(index) {
    todos[index].completed = !todos[index].completed;
    guardar();
    renderTodos();
}

function deleteTodo(index) {
    todos.splice(index, 1);
    guardar();
    renderTodos();
}

function deleteCompleted() {
    todos = todos.filter(t => !t.completed);
    guardar();
    renderTodos();
}

function deleteAll() {
    todos = [];
    guardar();
    renderTodos();
}

// ===============================
// Renderizado del DOM
// ===============================
function renderTodos() {
    const lista = document.getElementById("lista-tareas");
    lista.innerHTML = "";

    // Filtrar según filtroActual
    let tareasFiltradas = todos;

    if (filtroActual === "activas") {
        tareasFiltradas = todos.filter(t => !t.completed);
    }

    if (filtroActual === "completadas") {
        tareasFiltradas = todos.filter(t => t.completed);
    }

    // Renderizar tareas filtradas
    tareasFiltradas.forEach((todo, index) => {
        const li = document.createElement("li");
        li.className = "tarea";
        if (todo.completed) li.classList.add("completada");

        li.innerHTML = `
      <label class="check">
        <input type="checkbox" ${todo.completed ? "checked" : ""}>
        <span class="check-custom"></span>
      </label>
      <span class="texto-tarea">${todo.text}</span>
      <button class="btn btn-icon">✕</button>
    `;

        // Eventos
        li.querySelector("input").addEventListener("change", () => toggleTodo(index));
        li.querySelector("button").addEventListener("click", () => deleteTodo(index));

        lista.appendChild(li);
    });

    actualizarFiltroVisual();
}

// ===============================
// Filtros
// ===============================
function actualizarFiltroVisual() {
    const enlaces = document.querySelectorAll(".filtro");
    enlaces.forEach(enlace => enlace.classList.remove("activo"));

    if (filtroActual === "todas") {
        enlaces[0].classList.add("activo");
    } else if (filtroActual === "activas") {
        enlaces[1].classList.add("activo");
    } else if (filtroActual === "completadas") {
        enlaces[2].classList.add("activo");
    }
}

// ===============================
// Eventos del formulario y botones
// ===============================
document.addEventListener("DOMContentLoaded", () => {
    const form = document.getElementById("form-tarea");
    const entrada = document.getElementById("entrada-tarea");

    form.addEventListener("submit", e => {
        e.preventDefault();
        const texto = entrada.value.trim();
        if (!texto) return;

        addTodo(texto);
        entrada.value = "";
    });

    document.getElementById("btn-eliminar-completadas")
        .addEventListener("click", deleteCompleted);

    document.getElementById("btn-eliminar-todas")
        .addEventListener("click", () => {
            if (confirm("¿Eliminar todas las tareas?")) deleteAll();
        });

    // Eventos de filtros
    const filtros = document.querySelectorAll(".filtro");
    filtros[0].addEventListener("click", e => {
        e.preventDefault();
        filtroActual = "todas";
        renderTodos();
    });

    filtros[1].addEventListener("click", e => {
        e.preventDefault();
        filtroActual = "activas";
        renderTodos();
    });

    filtros[2].addEventListener("click", e => {
        e.preventDefault();
        filtroActual = "completadas";
        renderTodos();
    });

    renderTodos();
});
