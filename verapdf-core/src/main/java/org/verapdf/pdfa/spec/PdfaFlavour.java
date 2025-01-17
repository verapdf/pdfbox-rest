/**
 * 
 */
package org.verapdf.pdfa.spec;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Enumeration for PDF/A flavours, composed of version and level.
 *
 * @author <a href="mailto:carl@openpreservation.org">Carl Wilson</a>
 */
public enum PdfaFlavour {
	/** Special ID for the none case */
	NONE(IsoStandard.NONE, Level.NONE),
	/** PDF Version 1 Level A */
	PDFA_1_A(IsoStandard.ISO_19005_1, Level.LEVEL_A),
	/** PDF Version 1 Level B */
	PDFA_1_B(IsoStandard.ISO_19005_1, Level.LEVEL_A),
	/** PDF Version 2 Level A */
	PDFA_2_A(IsoStandard.ISO_19005_2, Level.LEVEL_A),
	/** PDF Version 2 Level B */
	PDFA_2_B(IsoStandard.ISO_19005_2, Level.LEVEL_B),
	/** PDF Version 3 Level A */
	PDFA_3_A(IsoStandard.ISO_19005_3, Level.LEVEL_A),
	/** PDF Version 3 Level B */
	PDFA_3_B(IsoStandard.ISO_19005_3, Level.LEVEL_B),
	/** PDF Version 3 Level U */
	PDFA_3_U(IsoStandard.ISO_19005_3, Level.LEVEL_U);

	private final IsoStandard standard;
	private final Level level;

	PdfaFlavour(final IsoStandard standard, final Level level) {
		this.standard = standard;
		this.level = level;
	}

    /**
	 * @return the specification part
	 */
	@JsonProperty
	public final IsoStandard getStandard() {
		return this.standard;
	}

	/**
	 * @return the level
	 */
	@JsonProperty
	public final Level getLevel() {
		return this.level;
	}

	@Override
	public String toString() {
		return this.getStandard().toString() + " " + this.getLevel().toString(); //$NON-NLS-1$
	}

	/**
	 * Enumeration of PDF/A Specification Parts, 1-3
	 * 
	 * @author <a href="mailto:carl@openpreservation.org">Carl Wilson</a>.</p>
	 */
	public static enum IsoStandard {
		/** PDF/A Version 1 */
		NONE(IsoStandardSeries.NONE,
				PdfaSpecifications.NONE_ID,
				PdfaSpecifications.NONE,
				PdfaSpecifications.NONE),
		/** PDF/A Version 1 */
		ISO_19005_1(IsoStandardSeries.ISO_19005,
				PdfaSpecifications.ISO_19005_1_PART,
				PdfaSpecifications.ISO_19005_1_YEAR,
				PdfaSpecifications.ISO_19005_1_DESCRIPTION),
		/** PDF/A Version 2 */
		ISO_19005_2(IsoStandardSeries.ISO_19005,
				PdfaSpecifications.ISO_19005_2_PART,
				PdfaSpecifications.ISO_19005_2_YEAR,
				PdfaSpecifications.ISO_19005_2_DESCRIPTION),
		/** PDF/A Version 3 */
		ISO_19005_3(IsoStandardSeries.ISO_19005,
				PdfaSpecifications.ISO_19005_3_PART,
				PdfaSpecifications.ISO_19005_3_YEAR,
				PdfaSpecifications.ISO_19005_3_DESCRIPTION);

		private final IsoStandardSeries series;
		private final int partNumber;
		private final String id;
		private final String year;
		private final String name;
		private final String description;

		IsoStandard(final IsoStandardSeries series, final int partNumber,
				final String year, final String description) {
			this.series = series;
			this.partNumber = partNumber;
			this.year = year;
			this.description = description;
			this.id = this.series.getName()
					+ "-" + String.valueOf(this.getPartNumber()) + ":" + this.getYear(); //$NON-NLS-1$//$NON-NLS-2$
			this.name = PdfaSpecifications.PDFA_STRING_PREFIX
					+ String.valueOf(this.getPartNumber());
		}

		/**
		 * @return the part number as an int
		 */
		@JsonProperty
		public final int getPartNumber() {
			return this.partNumber;
		}

		/**
		 * @return the standard part id as a String
		 */
		public final String getId() {
			return this.id;
		}

		/**
		 * @return the year for the standard part
		 */
		@JsonProperty
		public final String getYear() {
			return this.year;
		}

		/**
		 * @return the name for the standard part
		 */
		@JsonProperty
		public final String getName() {
			return this.name;
		}

		/**
		 * @return the description
		 */
		public String getDescription() {
			return this.description;
		}

		@Override
		public String toString() {
			return String
					.format("%s %s -- %s %s", this.getId(), this.series.getDescription(), this.getDescription(), this.getName()); //$NON-NLS-1$
		}
	}

	/**
	 * Enumeration for PDF/A Levels A, B & U
	 *
	 * @author <a href="mailto:carl@openpreservation.org">Carl Wilson</a>.</p>
	 */
	public static enum Level {
		/** Special identifier for the none case */
		NONE(PdfaSpecifications.NONE),
		/** Level A */
		LEVEL_A(PdfaSpecifications.LEVEL_A_CODE),
		/** Level B */
		LEVEL_B(PdfaSpecifications.LEVEL_B_CODE),
		/** Level U */
		LEVEL_U(PdfaSpecifications.LEVEL_U_CODE);

		private final String code;
		private final String fullName;

		Level(final String code) {
			this.code = code;
			this.fullName = PdfaSpecifications.LEVEL_PREFIX + this.getCode();
		}

		/**
		 * @return the PDF/A Level code ("a", "b", or "u")
		 */
		@JsonProperty
		public final String getCode() {
			return this.code;
		}

		@Override
		public String toString() {
			return this.fullName;
		}
	}

	/**
	 * Enum to for ISO standard identifiers
	 */
	public enum IsoStandardSeries {
		/** Special identifier for the none case */
		NONE(PdfaSpecifications.NONE_ID, PdfaSpecifications.NONE),
		/** Identifier for PDF/A ISO Standard */
		ISO_19005(PdfaSpecifications.ISO_19005_ID,
				PdfaSpecifications.ISO_19005_DESCRIPTION),
		/** Identifier for PDF 1.7 ISO Standard */
		ISO_32000(PdfaSpecifications.ISO_32000_ID,
				PdfaSpecifications.ISO_32000_DESCRIPTION);
		private final int id;
		private final String name;
		private final String description;

		IsoStandardSeries(final int id, final String description) {
			this.id = id;
			this.name = PdfaSpecifications.ISO_PREFIX + this.getId();
			this.description = description;
		}

		/**
		 * @return the id
		 */
		public int getId() {
			return this.id;
		}

		/**
		 * @return the name
		 */
		public String getName() {
			return this.name;
		}

		/**
		 * @return the description
		 */
		public String getDescription() {
			return this.description;
		}

		@Override
		public String toString() {
			return this.getName() + " " + this.getDescription(); //$NON-NLS-1$
		}
	}
}
