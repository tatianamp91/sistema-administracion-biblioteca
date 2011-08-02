package co.edu.utilities;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import java.util.ResourceBundle;

import javax.faces.FactoryFinder;
import javax.faces.application.Application;
import javax.faces.application.ApplicationFactory;
import javax.faces.application.FacesMessage;
import javax.faces.application.ViewHandler;
import javax.faces.component.UIInput;
import javax.faces.component.UIViewRoot;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.el.ValueBinding;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import co.edu.unicatolica.modelo.SabRol;
import co.edu.unicatolica.modelo.SabUsuario;

public class FacesUtils {

	public static ServletContext getServletContext() {
		return (ServletContext) FacesContext.getCurrentInstance()
				.getExternalContext().getContext();
	}

	public static FacesContext getFacesContext() {
		return FacesContext.getCurrentInstance();
	}

	public static ExternalContext getExternalContext() {
		FacesContext fc = FacesContext.getCurrentInstance();

		return fc.getExternalContext();
	}

	public static HttpSession getHttpSession(boolean create) {
		return (HttpSession) FacesContext.getCurrentInstance()
				.getExternalContext().getSession(create);
	}

	public static void refresh() {
		FacesContext context = FacesContext.getCurrentInstance();
		Application application = context.getApplication();
		ViewHandler viewHandler = application.getViewHandler();
		UIViewRoot viewRoot = viewHandler.createView(context, context
				.getViewRoot().getViewId());
		context.setViewRoot(viewRoot);
	}

	public static Object getManagedBean(String beanName) {
		// return
		// getValueBinding(getJsfEl(beanName)).getValue(FacesContext.getCurrentInstance());
		// ReportBean reportBean =
		// (ReportBean)facesContext.getApplication().getVariableResolver().resolveVariable(facesContext,
		// "reportBean");
		return FacesContext.getCurrentInstance().getApplication()
				.getVariableResolver()
				.resolveVariable(FacesContext.getCurrentInstance(), beanName);
	}

	public static Object getManagedBeanFromSession(String beanName) {
		return FacesContext.getCurrentInstance().getExternalContext()
				.getSessionMap().get(beanName);
	}

	public static void resetManagedBean(String beanName) {
		getValueBinding(getJsfEl(beanName)).setValue(
				FacesContext.getCurrentInstance(), null);
	}

	public static void setManagedBeanInSession(String beanName,
			Object managedBean) {
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap()
				.put(beanName, managedBean);
	}

	public static String getRequestParameter(String name) {
		return (String) FacesContext.getCurrentInstance().getExternalContext()
				.getRequestParameterMap().get(name);
	}

	public static void addInfoMessage(String msg) {
		addInfoMessage(null, msg);
	}

	public static void addInfoMessage(String clientId, String msg) {
		FacesContext.getCurrentInstance().addMessage(clientId,
				new FacesMessage(FacesMessage.SEVERITY_INFO, msg, msg));
	}

	public static void addErrorMessage(String msg) {
		addErrorMessage(null, msg);
	}

	public static void addErrorMessage(String clientId, String msg) {
		FacesContext.getCurrentInstance().addMessage(clientId,
				new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, msg));
	}

	private static Application getApplication() {
		ApplicationFactory appFactory = (ApplicationFactory) FactoryFinder
				.getFactory(FactoryFinder.APPLICATION_FACTORY);

		return appFactory.getApplication();
	}

	private static ValueBinding getValueBinding(String el) {
		return getApplication().createValueBinding(el);
	}

	private static HttpServletRequest getServletRequest() {
		return (HttpServletRequest) FacesContext.getCurrentInstance()
				.getExternalContext().getRequest();
	}

	private static Object getElValue(String el) {
		return getValueBinding(el).getValue(FacesContext.getCurrentInstance());
	}

	private static String getJsfEl(String value) {
		return "#{" + value + "}";
	}

	public static HttpSession getSession() {
		return (HttpSession) FacesContext.getCurrentInstance()
				.getExternalContext().getSession(true);
	}

	public static void putinSession(String name, Object o) {
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance()
				.getExternalContext().getSession(true);
		session.setAttribute(name, o);
	}

	public static Object getfromSession(String name) {
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance()
				.getExternalContext().getSession(false);

		return session.getAttribute(name);
	}

	public static HttpServletRequest getRequest() {
		return (HttpServletRequest) FacesContext.getCurrentInstance()
				.getExternalContext().getRequest();
	}

	public static void putinRequest(String name, Object o) {
		HttpServletRequest request = (HttpServletRequest) FacesContext
				.getCurrentInstance().getExternalContext().getRequest();
		request.setAttribute(name, o);
	}

	public static Object getfromRequest(String name) {
		HttpServletRequest request = (HttpServletRequest) FacesContext
				.getCurrentInstance().getExternalContext().getRequest();

		return request.getAttribute(name);
	}

	public static Long checkLong(Object input) throws Exception {
		if (input == null) {
			return null;
		}

		UIInput inputValue = (UIInput) input;

		if (inputValue.getValue() == null) {
			return null;
		}

		if ((inputValue.getValue()).equals("")) {
			return null;
		}

		try {
			return new Long(inputValue.getValue().toString());
		} catch (Exception e) {
			throw new Exception(inputValue.getId());
		}
	}

	public static String checkString(Object input) throws Exception {
		if (input == null) {
			return null;
		}

		UIInput inputValue = (UIInput) input;

		if (inputValue.getValue() == null) {
			return null;
		}

		if ((inputValue.getValue()).equals("")) {
			return null;
		}

		try {
			return new String(inputValue.getValue().toString());
		} catch (Exception e) {
			throw new Exception(inputValue.getId());
		}
	}

	public static Integer checkInteger(Object input) throws Exception {
		if (input == null) {
			return null;
		}

		UIInput inputValue = (UIInput) input;

		if (inputValue.getValue() == null) {
			return null;
		}

		if ((inputValue.getValue()).equals("")) {
			return null;
		}

		try {
			return new Integer(inputValue.getValue().toString());
		} catch (Exception e) {
			throw new Exception(inputValue.getId());
		}
	}

	public static Double checkDouble(Object input) throws Exception {
		if (input == null) {
			return null;
		}

		UIInput inputValue = (UIInput) input;

		if (inputValue.getValue() == null) {
			return null;
		}

		if ((inputValue.getValue()).equals("")) {
			return null;
		}

		try {
			return new Double(inputValue.getValue().toString());
		} catch (Exception e) {
			throw new Exception(inputValue.getId());
		}
	}

	public static Date checkDate(Object input) throws Exception {
		if (input == null) {
			return null;
		}

		UIInput inputValue = (UIInput) input;

		if (inputValue.getValue() == null) {
			return null;
		}

		if ((inputValue.getValue()).equals("")) {
			return null;
		}

		try {
			return (Date) inputValue.getValue();
		} catch (Exception e) {
			throw new Exception(inputValue.getId());
		}
	}

	public static BigInteger checkBigInteger(Object input) throws Exception {
		if (input == null) {
			return null;
		}

		UIInput inputValue = (UIInput) input;

		if (inputValue.getValue() == null) {
			return null;
		}

		if ((inputValue.getValue()).equals("")) {
			return null;
		}

		try {
			return new BigInteger(inputValue.getValue().toString());
		} catch (Exception e) {
			throw new Exception(inputValue.getId());
		}
	}

	public static BigDecimal checkBigDecimal(Object input) throws Exception {
		if (input == null) {
			return null;
		}

		UIInput inputValue = (UIInput) input;

		if (inputValue.getValue() == null) {
			return null;
		}

		if ((inputValue.getValue()).equals("")) {
			return null;
		}

		try {
			return new BigDecimal(inputValue.getValue().toString());
		} catch (Exception e) {
			throw new Exception(inputValue.getId());
		}
	}

	protected static ResourceBundle getResourceBundle(String messagePropertyName) {
		ResourceBundle bundle = ResourceBundle.getBundle(messagePropertyName,
				FacesContext.getCurrentInstance().getViewRoot().getLocale());
		return bundle;
	}

	public static String getMensaje(String key) {
		try {
			String messageBundle = "co.edu.presentacion.etiquetas.mensajes";

			ResourceBundle bundle = getResourceBundle(messageBundle);
			return bundle.getString(key);
		} catch (Exception e) {
			return "??" + key + "??";
		}
	}

	public static String getEtiqueta(String key) {
		try {
			String messageBundle = "co.edu.presentacion.etiquetas.etiquetas";

			ResourceBundle bundle = getResourceBundle(messageBundle);
			return bundle.getString(key);
		} catch (Exception e) {
			return "??" + key + "??";
		}
	}

	public static String getParametros(String key) {
		try {
			String messageBundle = "co.edu.presentacion.etiquetas.parametros";

			ResourceBundle bundle = getResourceBundle(messageBundle);
			return bundle.getString(key);
		} catch (Exception e) {
			return "??" + key + "??";
		}
	}

	protected static Object getUserAuth() {
		return getfromSession("usuario");
	}

	public static String getUserName() {
		SabUsuario sabUsuario = (SabUsuario) getUserAuth();
		if (sabUsuario != null) {
			return sabUsuario.getNombreCompleto();
		} else {
			return null;
		}
	}

	public static Long getUserId() {
		SabUsuario sapUsuario = (SabUsuario) getUserAuth();
		if (sapUsuario != null) {
			return sapUsuario.getIdUsuario();
		} else {
			return null;
		}
	}
	
	public static String getUserCodigo() {
		SabUsuario sapUsuario = (SabUsuario) getUserAuth();
		if (sapUsuario != null) {
			return sapUsuario.getCodigo();
		} else {
			return null;
		}
	}

	public static SabRol getUserRol() {
		SabUsuario sapUsuario = (SabUsuario) getUserAuth();
		if (sapUsuario != null) {
			return sapUsuario.getSabRol();
		} else {
			return null;
		}
	}
}
