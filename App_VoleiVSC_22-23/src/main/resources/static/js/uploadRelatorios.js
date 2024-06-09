$(document).ready(function() {
    $('#uploadForm').on('submit', function(e) {
      e.preventDefault();
      var formData = new FormData(this);
      $.ajax({
        type: 'POST',
        url: '/relatorio/upload',
        data: formData,
        processData: false,
        contentType: false,
        success: function(result) {
          $('#uploadResult').html(result);
        },
        error: function() {
          alert('Erro ao enviar o arquivo.');
        }
      });
    });
  });
  