$(document).ready(function() {
    $('#pdfForm').on('submit', function(e) {
      e.preventDefault();
      var idRelatorio = $('#idRelatorio').val();
      $.ajax({
        type: 'GET',
        url: '/relatorio/ver',
        data: {
          'idRelatorio': idRelatorio
        },
        success: function(result) {
          $('#pdfViewer').html(result);
        },
        error: function() {
          alert('Erro ao carregar o relatório.');
        }
      });
    });
  });
  