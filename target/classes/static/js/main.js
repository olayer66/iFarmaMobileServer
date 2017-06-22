/*
 * Funciones basicas para la web
 */
//Funcion principal
$(function () {
    console.log("DOM inicializado");

    $('[data-modal-show-on-error="true"]').modal('show');

    $('[data-collapse-show-on-error="true"]').collapse('show');

    $('[data-ui-back]').on('click', function (e) {
        e.preventDefault();
        history.back()
    });

    $('#comboComunidades').on('change', function () {
      var $this = $(this);
      var $provincia = $('#comboProvincia');
      var comunidades = {
        "Andalucia": ["Almería", "Granada","Córdoba","Jaén","Sevilla","Málaga","Cádiz","Huelva"],
        "Aragon": ["Huesca","Zaragoza", "Teruel"],
        "Principado de Asturias": ["Asturias"],
        "Islas Baleares": ["Baleares"],
        "Canarias": ["Santa Cruz de Tenerife","Las Palmas"],
        "Cantabria": ["Santander"],
        "Castilla-La Mancha": ["Toledo"," Ciudad Real"," Cuenca"," Guadalajara "," Albacete"],
        "Castilla y Leon": ["León","Palencia","Salamanca","Burgos","Zamora","Valladolid","Soria","Segovia","Ávila"],
        "Catalunya": ["Barcelona","Tarragona","Lérida ","Gerona"],
        "Extremadura": ["Cáceres ","Badajoz"],
        "Galicia": ["La Coruña"," Lugo"," Orense ","Pontevedra"],
        "La Rioja": ["La Rioja"],
        "Comunidad de Madrid": ["Madrid"],
        "Navarra": ["Navarra"],
        "Pais Vasco": ["Álava"," Guipúzcoa ","Vizcaya"],
        "Region de Murcia": ["Murcia"],
        "Comunidad Valenciana": ["Castellón"," Valencia ","Alicante"],
        "Ceuta": ["Ceuta"],
        "Melilla": ["Melilla"]
      };

      $provincia.html('');

      $.each(comunidades[$this.val()], function () {
        $provincia.append('<option value="' + this + '">' + this + '</option>');
      });
    });

    $("#pagoEfectivo").hide();
    $("#pagoTarjeta").hide();
    $("#pagoPayPal").hide();

    $('.datePicker').datepicker({
        format: 'dd/mm/yyyy'
    }).on('changeDate', function(e) {
        // Revalidate the date field
        $('#eventForm').formValidation('revalidateField', 'date');
    });
    $('input[type=radio][name=formaPago]').change(function() {
        if (this.value == '0') {
            $("#pagoEfectivo").hide();
            $("#pagoTarjeta").hide();
            $("#pagoPayPal").show();
        }
        else if (this.value == '1') {
            $("#pagoEfectivo").hide();
            $("#pagoTarjeta").show();
            $("#pagoPayPal").hide();
        }
        else if (this.value == '2') {
            $("#pagoEfectivo").show();
            $("#pagoTarjeta").hide();
            $("#pagoPayPal").hide();
        }
    });
    $("#btnNuevousuario").on("click",function(){
        var codigo=$("codNuevoUsuario").val();
        if(codigo==="" || codigo===undefined || codigo===null)
            alert("Codigo no valido");
    });
    $('#tablaStock').DataTable({
        "paging": true,
        "lengthChange": true,
        "searching": true,
        "ordering": true,
        "info": true,
        "autoWidth": false
      });
      $('#tablaPedidos').DataTable({
        "paging": true,
        "lengthChange": true,
        "searching": true,
        "ordering": true,
        "info": true,
        "autoWidth": false
      });
      $('#tablaPedidosPc').DataTable({
          "paging": true,
          "lengthChange": true,
          "searching": true,
          "ordering": true,
          "info": true,
          "autoWidth": false
        });
      $('#tablaTratamiento').DataTable({
          "paging": true,
          "lengthChange": true,
          "searching": true,
          "ordering": true,
          "info": true,
          "autoWidth": false
        });
      $('#tablaAltaFarmacias').DataTable({
          "paging": true,
          "lengthChange": true,
          "searching": true,
          "ordering": true,
          "info": true,
          "autoWidth": false
        });
      $('#tablaAltaMedicos').DataTable({
          "paging": true,
          "lengthChange": true,
          "searching": true,
          "ordering": true,
          "info": true,
          "autoWidth": false
        });
      $('#tablaAltaFarmaceuticos').DataTable({
          "paging": true,
          "lengthChange": true,
          "searching": true,
          "ordering": true,
          "info": true,
          "autoWidth": false
        });
      $('#tablaMedicamento').DataTable({
          "paging": true,
          "lengthChange": true,
          "searching": true,
          "ordering": true,
          "info": true,
          "autoWidth": false
        });
});