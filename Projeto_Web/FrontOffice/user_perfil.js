window.onload = function() {
    var email = localStorage.getItem('loggedInUser');
    
    if (!email) {
        window.location.href = 'login.html';
        return;
    }

    function recuperarPerfis() {
        var perfis = localStorage.getItem('perfis');
        return perfis ? JSON.parse(perfis) : [];
    }

    var perfis = recuperarPerfis();
    var utilizador = perfis.find(function(perfil) {
        return perfil.email === email;
    });

    if (utilizador) {
        document.getElementById('name').value = utilizador.nome;
        document.getElementById('email').value = utilizador.email;
        document.getElementById('address').value = utilizador.morada;
        document.getElementById('phone').value = utilizador.telemovel;
    }

    
};
