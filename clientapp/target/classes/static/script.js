function fetchData() {
    const cursoId = document.getElementById("curso-id").value;
    if (!cursoId) {
        alert("O campo ID do curso é obrigatório!");
        return;
    }
    const uri = `http://localhost:8080/cursos/id/${cursoId}`;

    async function fetchCursoData() {
        const response = await fetch(uri);
        const dadosCurso = document.getElementById("curso-details");

        if (!response.ok) {
            alert("Não foi possível retornar os dados do curso!");
            document.getElementById("curso-id").value = "";
            dadosCurso.innerHTML = "";
        } else {
            const json = await response.json();
            dadosCurso.innerHTML = `
                <div class="info-card">
                    <p><strong>Nome:</strong> ${json.nome}</p>
                    <p><strong>Descrição:</strong> ${json.descricao}</p>
                    <p><strong>Carga Horária:</strong> ${json.cargaHoraria}</p>
                    <p><strong>Número de Vagas:</strong> ${json.numVagas}</p>
                    <p><strong>Data de Início:</strong> ${new Date(json.dataInicio).toLocaleDateString()}</p>
                    <p><strong>Data de Término:</strong> ${new Date(json.dataTermino).toLocaleDateString()}</p>
                    <p><strong>Status:</strong> ${json.status}</p>
                </div>
            `;
        }
    }
    fetchCursoData();
}

async function carregarTodosCursos() {
    const div = document.getElementById("lista-cursos-content");
    div.innerHTML = "Carregando cursos...";

    try {
        const resposta = await fetch("http://localhost:8080/cursos");
        if (!resposta.ok) throw new Error("Erro ao buscar cursos");

        const cursos = await resposta.json();

        if (cursos.length === 0) {
            div.innerHTML = "<p>Nenhum curso cadastrado.</p>";
            return;
        }

        let tabela = `<table>
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Nome</th>
                    <th>Descrição</th>
                    <th>Início</th>
                    <th>Término</th>
                    <th>Vagas</th>
                    <th>Carga Horária</th>
                    <th>Status</th>
                </tr>
            </thead>
            <tbody>`;

        cursos.forEach((curso) => {
            tabela += `<tr>
                <td>${curso.id}</td>
                <td>${curso.nome}</td>
                <td>${curso.descricao}</td>
                <td>${new Date(curso.dataInicio).toLocaleDateString()}</td>
                <td>${new Date(curso.dataTermino).toLocaleDateString()}</td>
                <td>${curso.numVagas}</td>
                <td>${curso.cargaHoraria}h</td>
                <td>${curso.status}</td>
            </tr>`;
        });

        tabela += "</tbody></table>";
        div.innerHTML = tabela;
    } catch (erro) {
        div.innerHTML = `<p class="error">Erro ao carregar cursos: ${erro.message}</p>`;
    }
}

function limparResultados() {
    document.getElementById("curso-id").value = "";
    document.getElementById("curso-details").innerHTML = "";
    document.getElementById("lista-cursos-content").innerHTML = "";
}
