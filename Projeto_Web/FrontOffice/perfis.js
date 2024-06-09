// Função para salvar os perfis no local storage
function salvarPerfis(perfis) {
    localStorage.setItem('perfis', JSON.stringify(perfis));
    console.log('Perfis salvos no localStorage:', perfis);
}

// Função para recuperar os perfis do local storage
function recuperarPerfis() {
    var perfis = localStorage.getItem('perfis');
    console.log('Perfis recuperados do localStorage:', perfis);
    return perfis ? JSON.parse(perfis) : [];
}

// Função para verificar a combinação de login/senha
function verificarLogin(username, password) {
    var perfis = recuperarPerfis();
    console.log('Perfis disponíveis para verificação de login:', perfis);
    return perfis.some(function(perfil) {
        return perfil.username === username && perfil.password === password;
    });
}

// Função para criar uma nova conta
function criarConta(username, password) {
    var perfis = recuperarPerfis();
    // Verificar se o username já existe
    if (perfis.some(function(perfil) { return perfil.username === username; })) {
        alert('Username já existe! Escolha outro!');
        return false;
    }


    // Adicionar o novo perfil e salvar
    perfis.push({ username: username, password: password });
    salvarPerfis(perfis);
    alert('Conta criada com sucesso!');
    return true;
}
