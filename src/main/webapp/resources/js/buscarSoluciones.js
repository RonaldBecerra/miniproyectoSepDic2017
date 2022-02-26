// Used by "misTeoremas.jsp"
function buscarSoluciones(idTeo){
    var url = window.location.href + "/listaSolucion";
    var data = {};
    data["teoid"] = idTeo;
    $("#modalLoading").css('display','inline-block');
    $.ajax({
        type:'POST',
        url: url,
        dataType:'json',
        data:data,
        success: function(data){
            $("#modalLoading").css('display','none');
            var i = 0;
            $('#listaSoluciones').html("");
            var len = Object.keys(data.soluciones).length;
            var i;
            for (i=1; i <= len; i++){
                var key = "Proof "+i;//(len-i+1);
                if (data.soluciones[key] == undefined)
                    key = "(Incomplete) Proof "+i;
                var link = "<a href='javascript:buscarFormula("+ data.soluciones[key] +","+ data.idTeo +");'> ";
                link = link + key + "</a>";//"Proof " + i + "</a>";
                var basurita = "<a href='javascript:delSol(" + data.soluciones[key] + ");'><i class=\"fa fa-trash\"></i></a>";
                link = link + " " + basurita;
                $('#listaSoluciones').append("<li>" + link + " </li>");

            }
            if(len != 0){
                $("#panelSoluciones").removeClass("d-none");
                $('#formula').html("");
            }
            else{
                alert("The selected theorem has no saved proofs.");
                $("#panelSoluciones").removeClass("d-none");
            }
        }  
    });
}

// Used by "misTeoremas.jsp"
function buscarMetaSoluciones(idTeo){
    $('#listaSoluciones').html("");
    var link = "<a href='javascript:buscarMetaFormula("+idTeo+");'> ";
    link = link + "Proof 1" + "</a>";
    $('#listaSoluciones').append("<li>" + link +  " </li>");
    $("#panelSoluciones").removeClass("d-none");
    $('#formula').html("");
}

function buscarFormula(idSol,idTeo){
    
    var data = {};
    var url = window.location.href + "/buscarFormula";
    data["idSol"] = idSol;
    data["idTeo"] = idTeo;
    $("#modalLoading").css('display','inline-block');
    $.ajax({
        type:'POST',
        url: url,
        dataType:'json',
        data:data,
        success: function(data){
            $("#modalLoading").css('display','none');
            $('#formula').html(data.historial);
            MathJax.Hub.Typeset();
        }
        
    });
}

function buscarMetaFormula(idTeo){
    var data = {};
    var url = window.location.href + "/buscarMetaFormula";
    data["idTeo"] = idTeo;
    $("#modalLoading").css('display','inline-block');
    $.ajax({
        type:'POST',
        url: url,
        dataType:'json',
        data:data,
        success: function(data){
            $("#modalLoading").css('display','none');
            $('#formula').html(data.historial);
            MathJax.Hub.Typeset();
        }
    });
}

function delSol(idSol){
    
    var data = {};
    var url = window.location.href + "/deleteSol/" + idSol;
    console.log(url);
    $("#modalLoading").css('display','inline-block');
    $.ajax({
        type:'POST',
        url: url,
        data:data,
        success: function(data){
            $("#modalLoading").css('display','none');
            alert(data);
            location.reload();
            //MathJax.Hub.Typeset();
        }
    });
}

function delTeo(idTeo){
    
    var data = {};
    var url = window.location.href + "/deleteTeo/" + idTeo;
    console.log(url);
    $("#modalLoading").css('display','inline-block');
    $.ajax({
        type:'POST',
        url: url,
        data:data,
        success: function(data){
            $("#modalLoading").css('display','none');
            alert(data);
            location.reload();
            //MathJax.Hub.Typeset();
        }
    });
}