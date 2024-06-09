document.getElementById('btn-register').addEventListener('click', function() {
    var nome = document.getElementById('nome').value;
    var email = document.getElementById('email').value;
    var morada = document.getElementById('morada').value;
    var telemovel = document.getElementById('telemovel').value;
    var password = document.getElementById('password').value;
    
    function recuperarPerfis() {
        var perfis = localStorage.getItem('perfis');
        return perfis ? JSON.parse(perfis) : [];
    }

    function salvarPerfis(perfis) {
        localStorage.setItem('perfis', JSON.stringify(perfis));
    }

    var perfis = recuperarPerfis();
    
    perfis.push({
        nome: nome,
        email: email,
        morada: morada,
        telemovel: telemovel,
        password: password
    });
    
    salvarPerfis(perfis);
    alert('Conta criada com sucesso! Faça login para continuar.');
    window.location.href = 'login.html';
});

document.getElementById('btn-redirect-login').addEventListener('click', function() {
    window.location.href = 'login.html';
});
