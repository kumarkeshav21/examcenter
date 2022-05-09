package nirmalya.aathithya.webmodule.user.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import nirmalya.aathithya.webmodule.common.utils.EnvironmentVaribles;
import nirmalya.aathithya.webmodule.common.utils.JsonResponse;
import nirmalya.aathithya.webmodule.user.model.Activity;
import nirmalya.aathithya.webmodule.user.model.Function;
import nirmalya.aathithya.webmodule.user.model.Menu;
import nirmalya.aathithya.webmodule.user.model.Module;
import nirmalya.aathithya.webmodule.user.model.User;

@Service
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

	@Autowired
	RestTemplate restTemplate;

	@Autowired
	EnvironmentVaribles env;

	@Autowired
	HttpSession session;

	@SuppressWarnings("unchecked")
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {

		// do some logic here if you want something to be done whenever
		// the user successfully logs in.
//		HttpSession session = request.getSession();
		/*
		 * HttpSessionManager httpSessionManager = (HttpSessionManager) request
		 * .getAttribute(HttpSessionManager.class.getName());
		 */

		CustomUserDetails userDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication()
				.getPrincipal();
			System.out.println("XXXXXXXXXXXXXXXXXXXXXXXxxxx"+userDetails);
		User user = userDetails.getUser();
		session.setAttribute("SESSION_AGENT", request.getHeader("User-Agent"));
		session.setAttribute("SESSION_ID", session.getId());
		session.setAttribute("SESSION_HOST", request.getRemoteHost());
		session.setAttribute("SESSION_PORT", request.getRemotePort());
		session.setAttribute("USER_ID", user.getUser());
		//session.setAttribute("USER_ID", user.getUser());
		session.setAttribute("USER_NAME", user.getUserName());
		session.setAttribute("USER_EMAIL", user.getUserEmail());
		session.setAttribute("VENDOR_ID", user.getVendorId());
		
		if (user.getUserType().contains("SuperAdmin")) {
			session.setAttribute("USER_ROLETYPE", "SuperAdmin");
		} else {
			session.setAttribute("USER_ROLETYPE", user.getUserType());
		}

		session.setAttribute("USER_MOBILE", user.getUserMobile());
		session.setAttribute("AUTHORITIES", authentication.getAuthorities());
		session.setAttribute("USER_ROLES", user.getRoles());

		session.setAttribute("DASHBOARD", user.getRoleDashboard());
		session.setAttribute("DATEFORMAT", user.getDateFormat());
		session.setAttribute("DATEFORMAT_ID", user.getDateFormatId());
		session.setAttribute("DATEFORMAT_JS", user.getDateFormatJS());
		StringBuffer roles = new StringBuffer();
		user.getRoles().stream().forEach(s -> roles.append(s));
		session.setAttribute("USER_ROLES_STRING", roles.toString());
		 
		JsonResponse<List<Menu>> jsonResponse = new JsonResponse<List<Menu>>();

		List<Module> module = new ArrayList<Module>();
		List<Menu> mList = new ArrayList<Menu>();
		List<String> uList = new ArrayList<String>();
		updateBasicAuditTrail(request, session);
		try {

			jsonResponse = restTemplate.postForObject(env.getUserUrl() + "getMenu", user.getRoles(),
					JsonResponse.class);

			ObjectMapper mapper = new ObjectMapper();
			mList = mapper.convertValue(jsonResponse.getBody(), new TypeReference<List<Menu>>() {
			});

			String pMod = "";
			if (mList != null && mList.size() > 0) {
				for (Menu m : mList) {

					String mod = m.getModule();

					if (mod.equals(pMod)) {

					} else {

						pMod = mod;

						String pFun = "";

						Module newMod = new Module();
						newMod.setName(mod);
						newMod.setModuleLogoName(m.getModuleLogo());
						newMod.setModuleId(m.getModuleId());
						List<Menu> fList = mList.stream().filter(s -> s.getModule().equals(mod))
								.collect(Collectors.toList());

						List<Function> funList = new ArrayList<Function>();

						for (Menu f : fList) {
							String fun = f.getFunction();

							if (fun.equals(pFun)) {

							} else {

								pFun = fun;
								List<Menu> aList = fList.stream().filter(s -> s.getFunction().equals(fun))
										.collect(Collectors.toList());
								List<Activity> sList = new ArrayList<Activity>();
								for (Menu a : aList) {
									Activity newAct = new Activity();
									newAct.setName(a.getActivity());
									newAct.setActivity(a.getUrl());
									newAct.setActivityId(a.getActivityId());

									uList.add(a.getUrl());

									if (a.getActivityStatus()) { // checking only active functions
										sList.add(newAct);
									}

								}

								Function newFun = new Function();
								newFun.setName(fun);
								newFun.setFunction(sList);
								newFun.setFunction(sList);
								newFun.setFunctionId(f.getFunctionId());
								funList.add(newFun);
								newMod.setFunctionId(fList.get(0).getFunctionId());
								newMod.setActivityId(fList.get(0).getActivityId());
							}

						}

						newMod.setModule(funList);
							//System.out.println("@@@@@===="+newMod);
						module.add(newMod);
					}
				}

			}

			/*
			 * adding extra urls to be accessed by all.
			 * 
			 */
			String dashboard = (String) session.getAttribute("DASHBOARD");
			uList.add(dashboard);

			session.setAttribute("MENU", module);
			session.setAttribute("MENU_COUNT", module.size());
			if (module.size() == 1) {
				session.setAttribute("MODULE_ID", mList.get(0).getModuleId());
			}
			session.setAttribute("URL_LIST", uList);
			session.setAttribute("loginMessage", null);

		} catch (RestClientException e) {
			e.printStackTrace();
		}
		// set our response to OK status
		response.setStatus(HttpServletResponse.SC_OK);

		// since we have created our custom success handler, its up to us to
		// where
		// we will redirect the user after successfully login
		/*
		 * Long time = new Date().getTime(); Cookie cookie = new Cookie("sessoionTime",
		 * time.toString()); response.addCookie(cookie);
		 */
		String dashboard = (String) session.getAttribute("DASHBOARD");
		/*
		 * String dashboard = (String) session.getAttribute("DASHBOARD"); if (module !=
		 * null) { if (module.size() == 1) {
		 * response.sendRedirect(mList.get(0).getUrl()); String url = httpSessionManager
		 * .encodeURL(dashboard, httpSessionManager.getCurrentSessionAlias(request)); //
		 * on login success add session alias in url response.sendRedirect(url);
		 * 
		 * } else { response.sendRedirect(dashboard); String url = httpSessionManager
		 * .encodeURL(dashboard, httpSessionManager.getCurrentSessionAlias(request)); //
		 * on login success add session alias in url response.sendRedirect(url); } }
		 * else { response.sendRedirect(dashboard); String url = httpSessionManager
		 * .encodeURL(dashboard, httpSessionManager.getCurrentSessionAlias(request)); //
		 * on login success add session alias in url response.sendRedirect(url); }
		 */

		response.sendRedirect(dashboard);

	}

	@SuppressWarnings("unchecked")
	private void updateBasicAuditTrail(HttpServletRequest request,HttpSession session) {
		
		@SuppressWarnings("unused")
		JsonResponse<Object> resp = new JsonResponse<Object>();
		
		String userAgent = request.getHeader("user-agent");
		System.out.println("#######"+userAgent);
		try {

			resp = restTemplate.getForObject(env.getUserUrl() + "browserdDetails?userAgent="+ userAgent,JsonResponse.class);
		} catch (RestClientException e) {
			e.printStackTrace();
		}
		
		
	}
	

}
