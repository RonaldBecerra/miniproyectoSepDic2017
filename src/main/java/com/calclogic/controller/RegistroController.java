package com.calclogic.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import com.calclogic.entity.Resuelve;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.calclogic.entity.Usuario;
import com.calclogic.entity.Materia;
import com.calclogic.forms.Registro;
import com.calclogic.service.ResuelveManager;
import com.calclogic.service.UsuarioManager;
import com.calclogic.service.MateriaManager;
import java.util.List;
import javax.validation.Valid;
import org.springframework.validation.ObjectError;
import org.apache.commons.codec.digest.DigestUtils;

@Controller
@RequestMapping(value="/registro")
public class RegistroController {
	
	@Autowired
	private UsuarioManager usuarioManager;
        @Autowired
        private ResuelveManager resuelveManager;
        @Autowired
        private MateriaManager materiaManager;
        
        @RequestMapping(method=RequestMethod.GET, params="new")
        public String createUsuarioProfile(ModelMap map)
        {
            List<Materia> list = materiaManager.getAllMaterias();
            map.addAttribute("registro",new Registro());
            map.addAttribute("materias", list);
            map.addAttribute("valueSubmit", "Check In");
            map.addAttribute("isRegistro", "1");
            return "registro";
        }
        
        @RequestMapping(method=RequestMethod.POST)
        public String addUsuarioFromForm(@Valid Registro registro, BindingResult bindingResult, ModelMap map)
        {
            Usuario user = usuarioManager.getUsuario(registro.getLogin());
            if( bindingResult.hasErrors() )
            {
                if (!registro.getPassword().equals(registro.getPasswordConf()))
                  bindingResult.rejectValue("passwordConf","error.registro","The password does not match");
                if (user != null)
                  bindingResult.rejectValue("login","error.registro","Username not available");
                List<Materia> list = materiaManager.getAllMaterias();
//                map.addAttribute("registro", registro);
                map.addAttribute("materias", list);
                map.addAttribute("valueSubmit", "Check In");
                map.addAttribute("isRegistro", "1");
                return "registro";
            }
            else{

                if (user != null || !registro.getPassword().equals(registro.getPasswordConf()))
                {
                  if (!registro.getPassword().equals(registro.getPasswordConf()))
                    bindingResult.rejectValue("passwordConf","error.registro","The password does not match");
                  if (user != null)
                    bindingResult.rejectValue("login","error.registro","Username not available");
                  List<Materia> list = materiaManager.getAllMaterias();
//                  map.addAttribute("registro", registro);
                  map.addAttribute("materias", list);
                  map.addAttribute("valueSubmit", "Check In");
                  map.addAttribute("isRegistro", "1");
                  return "registro";
                }

                Materia materia = materiaManager.getMateria(registro.getMateriaid());
                String randomchars = "hdfGLd6J4$&(3nd^{bHGF@fs";
                String pass = DigestUtils.sha512Hex(registro.getPassword()+randomchars);
                user = new Usuario(registro.getLogin(), registro.getNombre(), 
                                           registro.getApellido(), registro.getCorreo(), 
                                           pass, materia, false);
                /**
                List<Resuelve> resuelves = resuelveManager.getAllResuelveByUser("AdminTeoremas");
                usuarioManager.addUsuario(user);
                for(Resuelve resuelve : resuelves){
                    resuelve.setUsuario(user);
                    resuelveManager.addResuelve(resuelve);
                }
                */
            }  
            
            map.addAttribute("usuario", user);
            return "registrado";
        }
        
        /*@RequestMapping(value="/{username}", method=RequestMethod.GET)
        public String showUsuarioProfile(@PathVariable String username, ModelMap map) {
            map.addAttribute("usuario", usuarioManager.getUsuario(username));
            return "registrado";
        }*/
        public void setUsuarioManager(UsuarioManager usuarioManager) {
		this.usuarioManager = usuarioManager;
        }
}
