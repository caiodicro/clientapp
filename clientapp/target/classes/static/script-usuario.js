async function buscarUsuarioPorId() {
  const id = document.getElementById("usuario-id").value;
  const div = document.getElementById("usuario-details");
  div.innerHTML = "";

  if (!id || parseInt(id) < 1) {
    alert("Informe um ID válido.");
    return;
  }

  try {
    const response = await fetch(`http://localhost:8080/usuarios/id/${id}`);
    if (!response.ok) throw new Error("Usuário com esse ID não encontrado.");

    const usuario = await response.json();
    div.innerHTML = gerarCardUsuario(usuario);
  } catch (err) {
    div.innerHTML = `<p class="error">${err.message}</p>`;
  }
}

async function buscarUsuarioPorEmail() {
  const email = document.getElementById("usuario-email").value;
  const div = document.getElementById("usuario-details");
  div.innerHTML = "";

  if (!email) {
    alert("Informe o e-mail do usuário.");
    return;
  }

  try {
    const response = await fetch(`http://localhost:8080/usuarios/email/${email}`);
    if (!response.ok) throw new Error("Usuário com esse e-mail não encontrado.");

    const usuario = await response.json();
    div.innerHTML = gerarCardUsuario(usuario);
  } catch (err) {
    div.innerHTML = `<p class="error">${err.message}</p>`;
  }
}

async function buscarUsuariosPorStatus() {
  const status = document.getElementById("status").value;
  const div = document.getElementById("lista-usuarios-content");
  div.innerHTML = "";

  if (!status) {
    alert("Selecione um status.");
    return;
  }

  div.innerHTML = "Buscando usuários...";

  try {
    const response = await fetch(`http://localhost:8080/usuarios/status/${status}`);
    if (!response.ok) throw new Error("Erro ao buscar usuários.");

    const usuarios = await response.json();

    if (usuarios.length === 0) {
      div.innerHTML = "<p>Nenhum usuário encontrado com esse status.</p>";
      return;
    }

    let tabela = `
      <table>
        <thead>
          <tr>
            <th>ID</th>
            <th>Nome</th>
            <th>CPF</th>
            <th>Email</th>
            <th>Nascimento</th>
            <th>Endereço</th>
            <th>Status</th>
          </tr>
        </thead>
        <tbody>`;

    usuarios.forEach(u => {
      tabela += `
        <tr>
          <td>${u.id}</td>
          <td>${u.nome}</td>
          <td>${u.cpf}</td>
          <td>${u.email}</td>
          <td>${new Date(u.nascimento).toLocaleDateString()}</td>
          <td>${u.endereco}</td>
          <td>${u.status}</td>
        </tr>`;
    });

    tabela += `</tbody></table>`;
    div.innerHTML = tabela;
  } catch (err) {
    div.innerHTML = `<p class="error">${err.message}</p>`;
  }
}

function gerarCardUsuario(usuario) {
  return `
    <div class="info-card">
      <p><strong>Nome:</strong> ${usuario.nome}</p>
      <p><strong>CPF:</strong> ${usuario.cpf}</p>
      <p><strong>Email:</strong> ${usuario.email}</p>
      <p><strong>Data de Nascimento:</strong> ${new Date(usuario.nascimento).toLocaleDateString()}</p>
      <p><strong>Endereço:</strong> ${usuario.endereco}</p>
      <p><strong>Status:</strong> ${usuario.status}</p>
    </div>`;
}

function limparCampos() {
  document.getElementById("usuario-id").value = "";
  document.getElementById("usuario-email").value = "";
  document.getElementById("status").value = "";
  document.getElementById("usuario-details").innerHTML = "";
  document.getElementById("lista-usuarios-content").innerHTML = "";
}
