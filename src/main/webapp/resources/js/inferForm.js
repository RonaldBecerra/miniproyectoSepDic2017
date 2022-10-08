$(function() {
    var form = $('#inferForm');
    
    $("#BtnInferir").click(function(ev){ 
        if (buttonsEnabled){     
            ev.preventDefault();
            $("#Btn").val("Inferir");
            var formData = form.serialize();
            $("#loadingModal").css('display','inline-block');
            $.ajax({
                url: $(form).attr('action'),
                type: 'POST',
                dataType: 'json',
                data: formData,
                success: function(data) {
                    $("#loadingModal").css('display','none');
                    if(data.errorParser2 !== null){
                        alert(data.Parser2);
                    }
                    else if(data.errorParser3 !== null){
                        alert(data.Parser3);
                    }
                    else{
                        $('#formula').html(data.historial);
                        MathJax.Hub.Typeset();
                        var proof = $('.proof');
                        proof.scrollTop(proof[0].scrollHeight);
                        /*var nSol = $('#nSolucion').val();
                        if(nSol==="new"){
                            $('#nSolucion').val(data.nSol);
                            nSol = $('#nSolucion').val();
                            var url = $(form).attr('action');
                            url = url.replace("new",nSol);
                            $(form).attr('action',url);
                        }*/
                        if(data.resuelto === "1"){
                            alert("Congratulations you have found a proof of the theorem!!");
                            window.location = $("#linkMyTheorems").attr("href");
                        }

                        // Verifies if it has ended a proof for a case.
                        if (data.endCase) {
                            alert("Congratulations you have found a proof of a case!");
                
                            $("#metodosDiv").show();
                			$('#metodosDemostracion').val("0");
                			let message = "Please, select a proof method for the case.";
                            openModalWithConfirmation(message);
                        }

                        if (data.valid) {
                            $('#nStatement_id').val("");
                            $('#instanciacion_id').val("");
                            $('#leibniz_id').val("");
                            $('#stbox').text("");
                            cleanJax('leibnizSymbolsId');
                            cleanJaxSubstitution('substitutionButtonsId');
                        }
                        //$("#nuevoMetodo").val("0");
                    }
                    
                },
                error: function(XMLHttpRequest, textStatus, errorThrown) { 
                    $("#loadingModal").css('display','none');
                    alert("Status: " + textStatus); alert("Error: " + errorThrown);
                }
            });
        }
    });
    
    $("#BtnRetroceder").click(function(ev){
        if (buttonsEnabled){    
            ev.preventDefault();
            $("#Btn").val("Retroceder");
            var formData = form.serialize();
            $("#loadingModal").css('display','inline-block');
            $.ajax({
                type: 'POST',
                url: $(form).attr('action'),
                dataType: 'json',
                data: formData,
                success: function(data) {
                    $("#loadingModal").css('display','none');
                    $('#formula').html(data.historial);
                    MathJax.Hub.Typeset();
                    var proof = $('.proof');
                    proof.scrollTop(proof[0].scrollHeight);
                    $('#nStatement_id').val("");
                    $('#instanciacion_id').val("");
                    $('#leibniz_id').val("");
                    $('#stbox').text("");
                    cleanJax('leibnizSymbolsId');
                    cleanJaxSubstitution('substitutionButtonsId');
                    setForms(data.cambiarMetodo);                    
                },
                error: function(XMLHttpRequest, textStatus, errorThrown) { 
                  $("#loadingModal").css('display','none');
                  alert("Status: " + textStatus); alert("Error: " + errorThrown); 
                }
            });
        }
    });
    
    $('#BtnLimpiar').click(function(){
        $('#nStatement_id').val("");
        $('#instanciacion_id').val("");
        $('#leibniz_id').val("");
        $('#stbox').text("");
        document.getElementById('substitutionButtonsId.SubstitutionDiv').children[0].innerHTML = "Substitution:";
    });
    
});

function hasNumericClass(element){
    let clases = $(element).attr("class");
    isNumeric = false;
    if (clases){
        clases = clases.split(" ");
        for (let i = 0; i<clases.length;i++){
            if ($.isNumeric(clases[i])){
                isNumeric = true
            }
        }
    }
    return isNumeric
    
}

function leibnizMouse(p1,p2){

    if (p1===p2) {
        $('#leibniz_id').val(inferRecoverC(leibniz[p1-0], leibnizLatex[p1-0],'leibnizSymbolsId_'));
        if (window['auto']){
            instantiationAjax("automaticSubst");
        }
        return;
    }

    /*var resp;
    var nivel;
    var padres = [];
    if(p1[0] == p2[0]){
    	resp = p1[0];
        
    	// Modify notation properly and set it to the view
        $('#leibniz_id').val(inferRecoverC(leibniz[resp], leibnizLatex[resp]));
        return;
    }  
    if(p1[1] <= p2[1]){
    	nivel = p1[1];
        padres[0] = p1[0];
    	padres[1] = $("#" + p2[0]).parents("." + nivel).attr("id");
    }
    else{
        
    	nivel = p2[1];
        padres[0] = $("#" + p1[0]).parents("." + nivel).attr("id");
    	padres[1] = p2[0];
    }
    if(padres[0] == padres[1]){
    	resp = padres[0];
    }
    else{
        closestCommonAncestor = $("#" + p1[0]).parents().has($("#" + p2[0])).first()[0];
        if (closestCommonAncestor){
            if (hasNumericClass(closestCommonAncestor)){
                resp = $(closestCommonAncestor).attr("id")
            }else{
                current = closestCommonAncestor
                while ($(current).parent().length > 0 && !hasNumericClass(current)){
                    current = $(current).parent()[0]
                }
                if (!hasNumericClass(current)){
                    resp = $(".0").attr("id")
                }else{
                    resp = $(current).attr("id")
                }
            }
        }else{
            resp = $("#" + padres[0]).parent().attr("id");      
        }
    }
    
    // Modify notation properly and set it to the view
    $('#leibniz_id').val(inferRecoverC(leibniz[resp], leibnizLatex[resp]));
    
    return;*/
}
