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
    var usuario = perfis.find(function(perfil) {
        return perfil.email === email;
    });

    if (usuario) {
        document.getElementById('name').value = usuario.nome;
        document.getElementById('email').value = usuario.email;
        document.getElementById('address').value = usuario.morada;
        document.getElementById('phone').value = usuario.telemovel;
    }

    document.getElementById('save-button').addEventListener('click', function() {
        var novoNome = document.getElementById('name').value;
        var novoEmail = document.getElementById('email').value;
        var novaMorada = document.getElementById('address').value;
        var novoTelemovel = document.getElementById('phone').value;

        // Atualizar os dados do usuário
        usuario.nome = novoNome;
        usuario.email = novoEmail;
        usuario.morada = novaMorada;
        usuario.telemovel = novoTelemovel;

        // Salvar os dados atualizados no localStorage
        localStorage.setItem('perfis', JSON.stringify(perfis));

        // Loga de novo e Redirecionar de volta para a página do perfil após salvar
        localStorage.setItem('loggedInUser', novoEmail);
        window.location.href = 'area_user.html';
    });
};
