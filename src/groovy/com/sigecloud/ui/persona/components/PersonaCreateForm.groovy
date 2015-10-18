package com.sigecloud.ui.persona.components

import com.sigecloud.PersonaService
import com.sigecloud.modelo.Persona
import com.sigecloud.ui.persona.views.PersonaListView
import com.sigecloud.util.ScNavigation
import com.sigecloud.util.TipoPersona
import com.sigecloud.validaciones.RucValidator
import com.vaadin.data.fieldgroup.BeanFieldGroup
import com.vaadin.data.fieldgroup.FieldGroup
import com.vaadin.data.validator.EmailValidator
import com.vaadin.data.validator.RegexpValidator
import com.vaadin.data.validator.StringLengthValidator
import com.vaadin.grails.Grails
import com.vaadin.ui.Button
import com.vaadin.ui.ComboBox
import com.vaadin.ui.CustomComponent
import com.vaadin.ui.FormLayout
import com.vaadin.ui.HorizontalLayout
import com.vaadin.ui.Notification
import com.vaadin.ui.TextArea
import com.vaadin.ui.TextField
import com.vaadin.ui.VerticalLayout


class PersonaCreateForm extends CustomComponent implements Button.ClickListener{

    Button guardarButton = new Button("Guardar")
    Button cancelButton = new Button("Cancelar")

    TextField nombre = new TextField("Nombre")
    TextField nombreComercial = new TextField("Nombre Comercial")
    TextField ruc = new TextField("RUC / Cédula")
    TextArea direccion = new TextArea("Dirección")
    TextField email = new TextField("Email")
    TextField telefono = new TextField("Teléfono")
    TextField celular = new TextField("Celular")
    ComboBox  tipoPersona = new ComboBox("Seleccione el Tipo")

    Persona persona = new Persona()
    BeanFieldGroup<Persona> formFieldBindings;

    PersonaCreateForm() {

        VerticalLayout verticalLayout = new VerticalLayout()

        /**
         * Botones
         */
        HorizontalLayout botonesLayout = new HorizontalLayout(guardarButton, cancelButton)
        botonesLayout.setMargin(true)
        botonesLayout.setSpacing(true)
        guardarButton.addClickListener(this)
        cancelButton.addClickListener(this)
        /**
         * Fin Botones
         */


        FormLayout formLayout = new FormLayout();

        tipoPersona.setInvalidAllowed(false);
        tipoPersona.setNullSelectionAllowed(false);
        TipoPersona.values().each {tipo -> tipoPersona.addItem(tipo)}

        formLayout.addComponent(botonesLayout)
        formLayout.addComponent(nombre)
        formLayout.addComponent(nombreComercial)
        formLayout.addComponent(ruc)
        formLayout.addComponent(direccion)
        formLayout.addComponent(email)
        formLayout.addComponent(telefono)
        formLayout.addComponent(celular)
        formLayout.addComponent(tipoPersona)

        //Valores por defecto para form
        nombre.setNullRepresentation("")
        nombreComercial.setNullRepresentation("")
        ruc.setNullRepresentation("")
        direccion.setNullRepresentation("")
        email.setNullRepresentation("")
        telefono.setNullRepresentation("")
        celular.setNullRepresentation("")

        /**
         * VALIDACIONES
         */

        nombre.addValidator(new StringLengthValidator("Debe ingresar un nombre", 5, 255, false))
        nombreComercial.addValidator(new StringLengthValidator("Debe ingresar un nombre", 5, 255, false))
        ruc.addValidator(new RucValidator())
        direccion.addValidator(new StringLengthValidator("Debe ingresar una direccion", 5, 255, false))
        email.addValidator(new EmailValidator("Email ingresado incorrecto"))
        telefono.addValidator(new RegexpValidator("\\d*", "Debe ingresar un numero válido"))
        celular.addValidator(new RegexpValidator("\\d*", "Debe ingresar un numero válido"))

        nombre.setImmediate(true);
        nombreComercial.setImmediate(true);
        ruc.setImmediate(true);
        direccion.setImmediate(true);
        email.setImmediate(true);
        telefono.setImmediate(true);
        celular.setImmediate(true);

        /**
         * FIN VALIDACIONES
         */

        /**
         * CONSTRAINTS
         */
        nombre.setMaxLength(255)
        nombreComercial.setMaxLength(255)
        ruc.setMaxLength(13)
        direccion.setMaxLength(500)
        email.setMaxLength(100)
        telefono.setMaxLength(20)
        celular.setMaxLength(20)
        /**
         * FIN CONSTRAINTS
         */

        /**
         * BINDING
         *
         * Debes enlazar los campos del objeto actual al bean para que funcione ojo
         * el this hace referecia a los campos de este objeto asi los relaciona
         */
        formFieldBindings = BeanFieldGroup.bindFieldsBuffered(persona, this)

        verticalLayout.addComponent(botonesLayout)
        verticalLayout.addComponent(formLayout)

        setCompositionRoot(verticalLayout);

    }

    @Override
    void buttonClick(Button.ClickEvent clickEvent) {
        if (clickEvent.getSource() == guardarButton){
            try{
                formFieldBindings.commit()
                Grails.get(PersonaService).save(persona)
                ScNavigation.navigateTo(PersonaListView.VIEW_NAME)
            }
            catch (FieldGroup.CommitException e) {
                Notification.show("You fail!");
            }
        }
        else {
            formFieldBindings.discard()
            ScNavigation.navigateTo(PersonaListView.VIEW_NAME)
        }

    }
}
