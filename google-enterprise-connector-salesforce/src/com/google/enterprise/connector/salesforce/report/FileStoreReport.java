/**
 * 
 */
package com.google.enterprise.connector.salesforce.report;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Logger;
import java.util.zip.GZIPInputStream;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.enterprise.connector.salesforce.storetype.FileStore;

/**
 * File Store Report class Provides report for File Store. There are following
 * two variants 1. Full Report includes all the files available in file store 2.
 * Day-wise report, this report requires year, month and day as an input
 * 
 * @author husain.merchant
 * @version 1.0
 */
public class FileStoreReport extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static final Logger LOGGER = Logger.getLogger(FileStoreReport.class
			.getName());

	private static int caseCounter = 0;
	private static String year;
	private static String month;
	private static String day;
	private static String action;
	private static Set<String> finalCaseSet = new HashSet<String>();

	/**
	 * Returns a list of connector types.
	 *
	 * @param req
	 * @param res
	 * @throws IOException
	 */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		doPost(req, res);
	}

	/**
	 * Returns a list of connector types.
	 *
	 * @param req
	 * @param res
	 * @throws IOException
	 */
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		// Make sure this requester is OK
		PrintWriter out = res.getWriter();
		LOGGER.info("fileStore path : " + FileStore.fileStoreDirectory);
		caseCounter = 0;

		finalCaseSet = new HashSet<String>();

		year = req.getParameter("year");
		month = req.getParameter("month");
		day = req.getParameter("day");

		action = req.getParameter("form_action");
		LOGGER.info("Form Action : " + action);

		if (action != null && action.equals("GET")) {
			fetchData(out);
		}

		if (action != null && action.equals("DELETE")) {
			if (isNotNullOrEmpty(year) && isNotNullOrEmpty(month)
					&& isNotNullOrEmpty(day)) {
				deleteData(out);
			}
		}

	}

	private void fetchData(PrintWriter out) throws IOException {
		LOGGER.info("Inside fetchData");
		LOGGER.info("Values : " + year + " / " + month + " / " + day);
		out.println("<html><body>");
		out.println("<h1 align='center'>File Store Report</h1>");
		out.println("<table align='center' border='1'><tr><th>Date</th><th>File Name</th><th>Number of Cases</th><th>Case Number(s)</th></tr>");
		listOfFiles(FileStore.fileStoreDirectory, out);
		out.println("</table>");
		out.println("<h3 align='center'>Total Number of Cases Indexed : "
				+ finalCaseSet.size() + "</h3>");
		out.println("</html></body>");
		LOGGER.info("fetchData completed");
	}

	private void deleteData(PrintWriter out) throws IOException {
		LOGGER.info("Inside deleteData");
		LOGGER.info("Values : " + year + " / " + month + " / " + day);
		deleteFiles(FileStore.fileStoreDirectory, out);
		out.println("<html><body>");
		out.println("<h1 align='center'>Data Deleted Successfully for : "
				+ year + "/" + month + "/" + day + " </h1>");
		out.println();
		out.println("</html></body>");

		LOGGER.info("deleteData completed");
	}

	public static Set<String> readCaseNumberFromFile(String fileName)
			throws IOException {
		Set<String> caseNumberSet = new HashSet<String>();

		FileInputStream fin = new FileInputStream(fileName);
		GZIPInputStream gzis = new GZIPInputStream(fin);
		InputStreamReader xover = new InputStreamReader(gzis);
		BufferedReader is = new BufferedReader(xover);

		String line;
		while ((line = is.readLine()) != null) {
			if (line.contains("<sf:CaseNumber>")) {
				String caseNumber = line.replace("<sf:CaseNumber>", "");
				caseNumber = caseNumber.replace("</sf:CaseNumber>", "");
				caseNumber = caseNumber.trim();
				caseNumberSet.add(caseNumber);
			}

		}

		finalCaseSet.addAll(caseNumberSet);

		return caseNumberSet;
	}

	private void listOfFiles(String directoryName, PrintWriter out)
			throws IOException {
		File directory = new File(directoryName);

		// get all the files from a directory
		String[] fList = directory.list();
		for (String file : fList) {
			if (year != null && year.length() > 0 && !year.equals(file)) {
				continue;
			}
			File directory1 = new File(directoryName + "/" + file);
			String[] fList1 = directory1.list();
			for (String file1 : fList1) {
				if (month != null && month.length() > 0 && !month.equals(file1)) {
					continue;
				}
				File directory2 = new File(directoryName + "/" + file + "/"
						+ file1);
				String[] fList2 = directory2.list();
				for (String file2 : fList2) {
					if (day != null && day.length() > 0 && !day.equals(file2)) {
						continue;
					}
					File directory3 = new File(directoryName + "/" + file + "/"
							+ file1 + "/" + file2);
					String[] fList3 = directory3.list();
					if (fList3 == null) {
						continue;
					}
					for (String file3 : fList3) {
						if (!file3.endsWith(".gz")) {
							continue;
						}
						Set<String> caseNumberSet = FileStoreReport
								.readCaseNumberFromFile(directoryName + "/"
										+ file + "/" + file1 + "/" + file2
										+ "/" + file3);
						out.println("<tr><td>" + file + "/" + file1 + "/"
								+ file2 + "</td>");
						out.println("<td>" + file3 + "</td>");
						out.println("<td>" + caseNumberSet.size() + "</td>");
						out.println("<td>" + caseNumberSet + "</td></tr>");
						caseCounter = caseCounter + caseNumberSet.size();
					}
				}
			}
		}
	}

	private void deleteFiles(String directoryName, PrintWriter out)
			throws IOException {
		File directory = new File(directoryName);

		// get all the files from a directory
		String[] fList = directory.list();
		for (String file : fList) {
			if (year != null && year.length() > 0 && !year.equals(file)) {
				continue;
			}
			File directory1 = new File(directoryName + "/" + file);
			String[] fList1 = directory1.list();
			for (String file1 : fList1) {
				if (month != null && month.length() > 0 && !month.equals(file1)) {
					continue;
				}
				File directory2 = new File(directoryName + "/" + file + "/"
						+ file1);
				String[] fList2 = directory2.list();
				for (String file2 : fList2) {
					if (day != null && day.length() > 0 && !day.equals(file2)) {
						continue;
					}
					File directory3 = new File(directoryName + "/" + file + "/"
							+ file1 + "/" + file2);
					deleteDir(directory3);
				}
			}
		}
	}

	private void deleteDir(File file) {
		File[] contents = file.listFiles();
		if (contents != null) {
			for (File f : contents) {
				deleteDir(f);
			}
		}
		file.delete();
	}

	private boolean isNotNullOrEmpty(String str) {
		return str != null && !str.trim().equals("");
	}

	public static void main(String args[]) throws Exception {
		FileStoreReport readGZIP = new FileStoreReport();
		readGZIP.listOfFiles("filestore", null);
	}
}
