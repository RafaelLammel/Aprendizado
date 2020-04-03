function createTask(e) {
    if (e.key == 'Enter') {
        fetch("/Home/Index", {
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            method: 'POST',
            body: {
                Id: undefined,
                Descricao: document.getElementById('descricao-tarefa').value,
                Status: false,
                IdLista: undefined,
            }
        }).then(function(res) {
            res.json().then(function(json) {

                let list = document.getElementById("collapseExample");

                let element = `<li id="tarefa-${json.id}">
        <span><i class="fas fa-trash trash" onclick="deleteTask(${json.id})"></i></span>
        ${json.descricao}
        <i id="tarefa-checked-@(item.Id)" class="fas fa-check check" onclick="changeStatus(${json.id})"></i>
        </li>`

                list.appendChild(element)

            })
        })
    }
}

function changeStatus(idTarefa) {
    fetch(`/Home/CompletaTarefa?idtarefa=${idTarefa}`).then(function(res) {
        res.json().then(function(json) {
            let id = json.id;
            let tarefa = document.getElementById("tarefa-checked-" + id);
            tarefa.classList.toggle("checked");
        })
    })
}

function deleteTask(idTarefa) {
    fetch(`Home/DeletaTarefa?idtarefa=${idTarefa}`).then(function(res) {
        res.json().then(function(json) {
            let id = json.id;
            let tarefa = document.getElementById("tarefa-" + id);
            tarefa.remove();
        })
    });
}