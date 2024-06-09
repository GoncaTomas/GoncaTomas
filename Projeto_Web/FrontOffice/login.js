document.getElementById('btn-login').addEventListener('click', function() {
    var email = document.getElementById('email').value;
    var password = document.getElementById('password').value;
    
    function recuperarPerfis() {
        var perfis = localStorage.getItem('perfis');
        return perfis ? JSON.parse(perfis) : [];
    }

    var perfis = recuperarPerfis();
    
    var loginValido = perfis.some(function(perfil) {
        return perfil.email === email && perfil.password === password;
    });

    if (loginValido) {
        localStorage.setItem('loggedInUser', email);
        
        // Verifica se o email termina com '@backoffice.com'
        if (email.endsWith('@backoffice.com')) {
            // Redireciona para o caminho correto do BackOffice
            window.location.href = '/BackOffice/AdminLTE-3.2.0/index.html';
        } else {
            // Redireciona para a área do usuário padrão
            window.location.href = 'area_user.html';
        }
    } else {
        alert('Email ou senha inválidos. Por favor, tente novamente.');
    }
});

// Pré-preenchimento do formulário de login se o usuário já estiver logado
document.addEventListener('DOMContentLoaded', function() {
    var loggedInUser = localStorage.getItem('loggedInUser');
    if (loggedInUser) {
        document.getElementById('email').value = loggedInUser;
    }
});
