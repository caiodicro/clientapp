async function buscarMatriculaPorId() {
  const id = document.getElementById("matricula-id").value;
  const div = document.getElementById("matricula-details");
  div.innerHTML = "";

  if (!id || parseInt(id) < 1) {
    alert("Informe um ID válido.");
    return;
  }

  try {
    const response = await fetch(`http://localhost:8080/matriculas/id/${id}`);
    if (!response.ok) throw new Error("Matrícula não encontrada.");

    const matricula = await response.json();
    div.innerHTML = gerarCardMatricula(matricula);
  } catch (err) {
    div.innerHTML = `<p class="error">${err.message}</p>`;
  }
}

async function buscarTodasMatriculas() {
  const div = document.getElementById("lista-matriculas");
  div.innerHTML = "Buscando matrículas...";

  try {
    const response = await fetch("http://localhost:8080/matriculas");
    if (!response.ok) throw new Error("Erro ao buscar matrículas.");

    const matriculas = await response.json();
    if (matriculas.length === 0) {
      div.innerHTML = "<p>Nenhuma matrícula encontrada.</p>";
      return;
    }

    let tabela = `
      <table>
        <thead>
          <tr>
            <th>ID</th>
            <th>Aluno</th>
            <th>Curso</th>
            <th>Data Início</th>
            <th>Data Fim</th>
          </tr>
        </thead>
        <tbody>`;

    matriculas.forEach(m => {
      tabela += `
        <tr>
          <td>${m.id}</td>
          <td>${m.usuario?.nome || "Não informado"}</td>
          <td>${m.curso?.nome || "Não informado"}</td>
          <td>${new Date(m.dataInicio).toLocaleDateString()}</td>
          <td>${new Date(m.dataFim).toLocaleDateString()}</td>
        </tr>`;
    });

    tabela += "</tbody></table>";
    div.innerHTML = tabela;
  } catch (err) {
    div.innerHTML = `<p class="error">${err.message}</p>`;
  }
}

function gerarCardMatricula(m) {
  return `
    <div class="info-card">
      <p><strong>ID Matrícula:</strong> ${m.id}</p>
      <p><strong>Aluno:</strong> ${m.usuario?.nome || "Não informado"}</p>
      <p><strong>Curso:</strong> ${m.curso?.nome || "Não informado"}</p>
      <p><strong>Data Início:</strong> ${new Date(m.dataInicio).toLocaleDateString()}</p>
      <p><strong>Data Fim:</strong> ${new Date(m.dataFim).toLocaleDateString()}</p>
    </div>`;
}

function limparMatricula() {
  document.getElementById("matricula-id").value = "";
  document.getElementById("matricula-details").innerHTML = "";
  document.getElementById("lista-matriculas").innerHTML = "";
}
