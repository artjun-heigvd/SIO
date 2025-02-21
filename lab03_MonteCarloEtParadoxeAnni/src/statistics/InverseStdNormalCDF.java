package statistics;

/**
 * Utility class to compute standard normal distribution quantiles.
 */
public final class InverseStdNormalCDF {

	// Precomputed values of the inverse standard normal CDF with 0.001 increments
	private static final double[][] invStdNormalCDF = {{0.5, 0}, {0.501, 0.00250663089957176}, {0.502,
			0.00501327754892665}, {0.503, 0.00751995569854052}, {0.504,
			0.0100266811002748}, {0.505, 0.0125334695080693}, {0.506,
			0.0150403366786357}, {0.507, 0.0175472983721508}, {0.508,
			0.0200543703529506}, {0.509, 0.0225615683902247}, {0.51,
			0.0250689082587110}, {0.511, 0.0275764057393917}, {0.512,
			0.0300840766201891}, {0.513, 0.0325919366966630}, {0.514,
			0.0351000017727088}, {0.515, 0.0376082876612559}, {0.516,
			0.0401168101849681}, {0.517, 0.0426255851769443}, {0.518,
			0.0451346284814213}, {0.519, 0.0476439559544765}, {0.52,
			0.0501535834647336}, {0.521, 0.0526635268940684}, {0.522,
			0.0551738021383167}, {0.523, 0.0576844251079841}, {0.524,
			0.0601954117289566}, {0.525, 0.0627067779432138}, {0.526,
			0.0652185397095437}, {0.527, 0.0677307130042591}, {0.528,
			0.0702433138219167}, {0.529, 0.0727563581760374}, {0.53,
			0.0752698620998298}, {0.531, 0.0777838416469152}, {0.532,
			0.0802983128920550}, {0.533, 0.0828132919318813}, {0.534,
			0.0853287948856291}, {0.535, 0.0878448378958717}, {0.536,
			0.0903614371292587}, {0.537, 0.0928786087772565}, {0.538,
			0.0953963690568919}, {0.539, 0.0979147342114994}, {0.54,
			0.100433720511470}, {0.541, 0.102953344255004}, {0.542,
			0.105473621768868}, {0.543, 0.107994569409154}, {0.544,
			0.110516203562042}, {0.545, 0.113038540644565}, {0.546,
			0.115561597105383}, {0.547, 0.118085389425553}, {0.548,
			0.120609934119307}, {0.549, 0.123135247734837}, {0.55,
			0.125661346855074}, {0.551, 0.128188248098486}, {0.552,
			0.130715968119863}, {0.553, 0.133244523611124}, {0.554,
			0.135773931302112}, {0.555, 0.138304207961405}, {0.556,
			0.140835370397127}, {0.557, 0.143367435457767}, {0.558,
			0.145900420032994}, {0.559, 0.148434341054490}, {0.56,
			0.150969215496777}, {0.561, 0.153505060378057}, {0.562,
			0.156041892761050}, {0.563, 0.158579729753844}, {0.564,
			0.161118588510745}, {0.565, 0.163658486233141}, {0.566,
			0.166199440170359}, {0.567, 0.168741467620538}, {0.568,
			0.171284585931507}, {0.569, 0.173828812501662}, {0.57,
			0.176374164780861}, {0.571, 0.178920660271312}, {0.572,
			0.181468316528477}, {0.573, 0.184017151161980}, {0.574,
			0.186567181836519}, {0.575, 0.189118426272792}, {0.576,
			0.191670902248420}, {0.577, 0.194224627598883}, {0.578,
			0.196779620218467}, {0.579, 0.199335898061207}, {0.58,
			0.201893479141851}, {0.581, 0.204452381536821}, {0.582,
			0.207012623385187}, {0.583, 0.209574222889649}, {0.584,
			0.212137198317524}, {0.585, 0.214701568001745}, {0.586,
			0.217267350341863}, {0.587, 0.219834563805069}, {0.588,
			0.222403226927206}, {0.589, 0.224973358313811}, {0.59,
			0.227544976641149}, {0.591, 0.230118100657266}, {0.592,
			0.232692749183045}, {0.593, 0.235268941113280}, {0.594,
			0.237846695417749}, {0.595, 0.240426031142308}, {0.596,
			0.243006967409982}, {0.597, 0.245589523422081}, {0.598,
			0.248173718459313}, {0.599, 0.250759571882916}, {0.6,
			0.253347103135800}, {0.601, 0.255936331743693}, {0.602,
			0.258527277316310}, {0.603, 0.261119959548518}, {0.604,
			0.263714398221530}, {0.605, 0.266310613204095}, {0.606,
			0.268908624453710}, {0.607, 0.271508452017839}, {0.608,
			0.274110116035147}, {0.609, 0.276713636736747}, {0.61,
			0.279319034447454}, {0.611, 0.281926329587062}, {0.612,
			0.284535542671622}, {0.613, 0.287146694314745}, {0.614,
			0.289759805228914}, {0.615, 0.292374896226804}, {0.616,
			0.294991988222626}, {0.617, 0.297611102233480}, {0.618,
			0.300232259380722}, {0.619, 0.302855480891349}, {0.62,
			0.305480788099397}, {0.621, 0.308108202447355}, {0.622,
			0.310737745487592}, {0.623, 0.313369438883806}, {0.624,
			0.316003304412483}, {0.625, 0.318639363964375}, {0.626,
			0.321277639545997}, {0.627, 0.323918153281133}, {0.628,
			0.326560927412373}, {0.629, 0.329205984302651}, {0.63,
			0.331853346436817}, {0.631, 0.334503036423212}, {0.632,
			0.337155076995277}, {0.633, 0.339809491013167}, {0.634,
			0.342466301465391}, {0.635, 0.345125531470472}, {0.636,
			0.347787204278627}, {0.637, 0.350451343273461}, {0.638,
			0.353117971973689}, {0.639, 0.355787114034875}, {0.64,
			0.358458793251194}, {0.641, 0.361133033557212}, {0.642,
			0.363809859029696}, {0.643, 0.366489293889434}, {0.644,
			0.369171362503090}, {0.645, 0.371856089385075}, {0.646,
			0.374543499199443}, {0.647, 0.377233616761812}, {0.648,
			0.379926467041307}, {0.649, 0.382622075162534}, {0.65,
			0.385320466407568}, {0.651, 0.388021666217977}, {0.652,
			0.390725700196870}, {0.653, 0.393432594110966}, {0.654,
			0.396142373892698}, {0.655, 0.398855065642337}, {0.656,
			0.401570695630148}, {0.657, 0.404289290298579}, {0.658,
			0.407010876264466}, {0.659, 0.409735480321281}, {0.66,
			0.412463129441405}, {0.661, 0.415193850778427}, {0.662,
			0.417927671669482}, {0.663, 0.420664619637616}, {0.664,
			0.423404722394183}, {0.665, 0.426148007841278}, {0.666,
			0.428894504074202}, {0.667, 0.431644239383956}, {0.668,
			0.434397242259781}, {0.669, 0.437153541391722}, {0.67,
			0.439913165673234}, {0.671, 0.442676144203821}, {0.672,
			0.445442506291720}, {0.673, 0.448212281456609}, {0.674,
			0.450985499432371}, {0.675, 0.453762190169879}, {0.676,
			0.456542383839841}, {0.677, 0.459326110835663}, {0.678,
			0.462113401776377}, {0.679, 0.464904287509595}, {0.68,
			0.467698799114508}, {0.681, 0.470496967904941}, {0.682,
			0.473298825432437}, {0.683, 0.476104403489395}, {0.684,
			0.478913734112256}, {0.685, 0.481726849584730}, {0.686,
			0.484543782441079}, {0.687, 0.487364565469441}, {0.688,
			0.490189231715209}, {0.689, 0.493017814484465}, {0.69,
			0.495850347347453}, {0.691, 0.498686864142122}, {0.692,
			0.501527398977708}, {0.693, 0.504371986238382}, {0.694,
			0.507220660586946}, {0.695, 0.510073456968595}, {0.696,
			0.512930410614728}, {0.697, 0.515791557046828}, {0.698,
			0.518656932080391}, {0.699, 0.521526571828932}, {0.7,
			0.524400512708041}, {0.701, 0.527278791439508}, {0.702,
			0.530161445055519}, {0.703, 0.533048510902909}, {0.704,
			0.535940026647490}, {0.705, 0.538836030278450}, {0.706,
			0.541736560112817}, {0.707, 0.544641654799999}, {0.708,
			0.547551353326401}, {0.709, 0.550465695020113}, {0.71,
			0.553384719555673}, {0.711, 0.556308466958918}, {0.712,
			0.559236977611907}, {0.713, 0.562170292257926}, {0.714,
			0.565108452006584}, {0.715, 0.568051498338983}, {0.716,
			0.570999473112987}, {0.717, 0.573952418568573}, {0.718,
			0.576910377333272}, {0.719, 0.579873392427705}, {0.72,
			0.582841507271216}, {0.721, 0.585814765687599}, {0.722,
			0.588793211910920}, {0.723, 0.591776890591447}, {0.724,
			0.594765846801678}, {0.725, 0.597760126042478}, {0.726,
			0.600759774249319}, {0.727, 0.603764837798630}, {0.728,
			0.606775363514265}, {0.729, 0.609791398674080}, {0.73,
			0.612812991016627}, {0.731, 0.615840188747972}, {0.732,
			0.618873040548629}, {0.733, 0.621911595580624}, {0.734,
			0.624955903494687}, {0.735, 0.628006014437570}, {0.736,
			0.631061979059499}, {0.737, 0.634123848521770}, {0.738,
			0.637191674504475}, {0.739, 0.640265509214374}, {0.74,
			0.643345405392917}, {0.741, 0.646431416324407}, {0.742,
			0.649523595844325}, {0.743, 0.652621998347801}, {0.744,
			0.655726678798253}, {0.745, 0.658837692736188}, {0.746,
			0.661955096288162}, {0.747, 0.665078946175923}, {0.748,
			0.668209299725723}, {0.749, 0.671346214877805}, {0.75,
			0.674489750196082}, {0.751, 0.677639964877996}, {0.752,
			0.680796918764575}, {0.753, 0.683960672350682}, {0.754,
			0.687131286795469}, {0.755, 0.690308823933034}, {0.756,
			0.693493346283289}, {0.757, 0.696684917063051}, {0.758,
			0.699883600197341}, {0.759, 0.703089460330928}, {0.76,
			0.706302562840087}, {0.761, 0.709522973844608}, {0.762,
			0.712750760220043}, {0.763, 0.715985989610205}, {0.764,
			0.719228730439924}, {0.765, 0.722479051928063}, {0.766,
			0.725737024100805}, {0.767, 0.729002717805218}, {0.768,
			0.732276204723099}, {0.769, 0.735557557385111}, {0.77,
			0.738846849185214}, {0.771, 0.742144154395409}, {0.772,
			0.745449548180789}, {0.773, 0.748763106614909}, {0.774,
			0.752084906695491}, {0.775, 0.755415026360469}, {0.776,
			0.758753544504371}, {0.777, 0.762100540995067}, {0.778,
			0.765456096690878}, {0.779, 0.768820293458062}, {0.78,
			0.772193214188685}, {0.781, 0.775574942818884}, {0.782,
			0.778965564347545}, {0.783, 0.782365164855387}, {0.784,
			0.785773831524484}, {0.785, 0.789191652658222}, {0.786,
			0.792618717701712}, {0.787, 0.796055117262662}, {0.788,
			0.799500943132737}, {0.789, 0.802956288309393}, {0.79,
			0.806421247018240}, {0.791, 0.809895914735898}, {0.792,
			0.813380388213404}, {0.793, 0.816874765500163}, {0.794,
			0.820379145968461}, {0.795, 0.823893630338557}, {0.796,
			0.827418320704382}, {0.797, 0.830953320559838}, {0.798,
			0.834498734825740}, {0.799, 0.838054669877406}, {0.8,
			0.841621233572914}, {0.801, 0.845198535282050}, {0.802,
			0.848786685915967}, {0.803, 0.852385797957575}, {0.804,
			0.855995985492682}, {0.805, 0.859617364241912}, {0.806,
			0.863250051593420}, {0.807, 0.866894166636438}, {0.808,
			0.870549830195654}, {0.809, 0.874217164866483}, {0.81,
			0.877896295051229}, {0.811, 0.881587346996175}, {0.812,
			0.885290448829642}, {0.813, 0.889005730601025}, {0.814,
			0.892733324320856}, {0.815, 0.896473364001916}, {0.816,
			0.900225985701433}, {0.817, 0.903991327564401}, {0.818,
			0.907769529868055}, {0.819, 0.911560735067541}, {0.82,
			0.915365087842814}, {0.821, 0.919182735146820}, {0.822,
			0.923013826254980}, {0.823, 0.926858512816044}, {0.824,
			0.930716948904339}, {0.825, 0.934589291073480}, {0.826,
			0.938475698411569}, {0.827, 0.942376332597951}, {0.828,
			0.946291357961576}, {0.829, 0.950220941541015}, {0.83,
			0.954165253146194}, {0.831, 0.958124465421903}, {0.832,
			0.962098753913142}, {0.833, 0.966088297132373}, {0.834,
			0.970093276628737}, {0.835, 0.974113877059309}, {0.836,
			0.978150286262472}, {0.837, 0.982202695333470}, {0.838,
			0.986271298702238}, {0.839, 0.990356294213575}, {0.84,
			0.994457883209753}, {0.841, 0.998576270615660}, {0.842,
			1.00271166502655}, {0.843, 1.00686427879852}, {0.844,
			1.01103432814182}, {0.845, 1.01522203321703}, {0.846,
			1.01942761823437}, {0.847, 1.02365131155609}, {0.848,
			1.02789334580214}, {0.849, 1.03215395795931}, {0.85,
			1.03643338949379}, {0.851, 1.04073188646754}, {0.852,
			1.04504969965839}, {0.853, 1.04938708468410}, {0.854,
			1.05374430213067}, {0.855, 1.05812161768478}, {0.856,
			1.06251930227087}, {0.857, 1.06693763219277}, {0.858,
			1.07137688928021}, {0.859, 1.07583736104043}, {0.86,
			1.08031934081496}, {0.861, 1.08482312794196}, {0.862,
			1.08934902792428}, {0.863, 1.09389735260344}, {0.864,
			1.09846842033986}, {0.865, 1.10306255619960}, {0.866,
			1.10768009214780}, {0.867, 1.11232136724931}, {0.868,
			1.11698672787661}, {0.869, 1.12167652792549}, {0.87,
			1.12639112903880}, {0.871, 1.13113090083863}, {0.872,
			1.13589622116731}, {0.873, 1.14068747633762}, {0.874,
			1.14550506139270}, {0.875, 1.15034938037601}, {0.876,
			1.15522084661195}, {0.877, 1.16011988299752}, {0.878,
			1.16504692230560}, {0.879, 1.17000240750048}, {0.88,
			1.17498679206609}, {0.881, 1.18000054034773}, {0.882,
			1.18504412790781}, {0.883, 1.19011804189642}, {0.884,
			1.19522278143743}, {0.885, 1.20035885803086}, {0.886,
			1.20552679597252}, {0.887, 1.21072713279160}, {0.888,
			1.21596041970732}, {0.889, 1.22122722210557}, {0.89,
			1.22652812003661}, {0.891, 1.23186370873498}, {0.892,
			1.23723459916283}, {0.893, 1.24264141857788}, {0.894,
			1.24808481112755}, {0.895, 1.25356543847045}, {0.896,
			1.25908398042707}, {0.897, 1.26464113566108}, {0.898,
			1.27023762239315}, {0.899, 1.27587417914913}, {0.9,
			1.28155156554460}, {0.901, 1.28727056310794}, {0.902,
			1.29303197614424}, {0.903, 1.29883663264251}, {0.904,
			1.30468538522879}, {0.905, 1.31057911216813}, {0.906,
			1.31651871841826}, {0.907, 1.32250513673844}, {0.908,
			1.32853932885681}, {0.909, 1.33462228670019}, {0.91,
			1.34075503369022}, {0.911, 1.34693862611028}, {0.912,
			1.35317415454800}, {0.913, 1.35946274541826}, {0.914,
			1.36580556257227}, {0.915, 1.37220380899873}, {0.916,
			1.37865872862328}, {0.917, 1.38517160821344}, {0.918,
			1.39174377939633}, {0.919, 1.39837662079750}, {0.92,
			1.40507156030963}, {0.921, 1.41183007750081}, {0.922,
			1.41865370617274}, {0.923, 1.42554403708045}, {0.924,
			1.43250272082581}, {0.925, 1.43953147093846}, {0.926,
			1.44663206715898}, {0.927, 1.45380635894057}, {0.928,
			1.46105626918691}, {0.929, 1.46838379824566}, {0.93,
			1.47579102817917}, {0.931, 1.48328012733562}, {0.932,
			1.49085335524666}, {0.933, 1.49851306787998}, {0.934,
			1.50626172327824}, {0.935, 1.51410188761928}, {0.936,
			1.52203624173586}, {0.937, 1.53006758813783}, {0.938,
			1.53819885858406}, {0.939, 1.54643312225675}, {0.94,
			1.55477359459685}, {0.941, 1.56322364686628}, {0.942,
			1.57178681650986}, {0.943, 1.58046681839936}, {0.944,
			1.58926755705139}, {0.945, 1.59819313992282}, {0.946,
			1.60724789190022}, {0.947, 1.61643637111502}, {0.948,
			1.62576338623323}, {0.949, 1.63523401538865}, {0.95,
			1.64485362695147}, {0.951, 1.65462790235108}, {0.952,
			1.66456286120272}, {0.953, 1.67466488902433}, {0.954,
			1.68494076787191}, {0.955, 1.69539771027214}, {0.956,
			1.70604339688896}, {0.957, 1.71688601843104}, {0.958,
			1.72793432238842}, {0.959, 1.73919766528525}, {0.96,
			1.75068607125217}, {0.961, 1.76241029786239}, {0.962,
			1.77438191034496}, {0.963, 1.78661336549347}, {0.964,
			1.79911810683797}, {0.965, 1.81191067295260}, {0.966,
			1.82500682114640}, {0.967, 1.83842366924778}, {0.968,
			1.85217985876905}, {0.969, 1.86629574345811}, {0.97,
			1.88079360815125}, {0.971, 1.89569792399184}, {0.972,
			1.91103564754912}, {0.973, 1.92683657326391}, {0.974,
			1.94313375110507}, {0.975, 1.95996398454005}, {0.976,
			1.97736842818195}, {0.977, 1.99539331016782}, {0.978,
			2.01409081201814}, {0.979, 2.03352014925305}, {0.98,
			2.05374891063182}, {0.981, 2.07485473439331}, {0.982,
			2.09692742916434}, {0.983, 2.12007168974215}, {0.984,
			2.14441062091184}, {0.985, 2.17009037758456}, {0.986,
			2.19728637664105}, {0.987, 2.22621176931718}, {0.988,
			2.25712924448623}, {0.989, 2.29036787785527}, {0.99,
			2.32634787404084}, {0.991, 2.36561812686429}, {0.992,
			2.40891554581546}, {0.993, 2.45726339020544}, {0.994,
			2.51214432793046}, {0.995, 2.57582930354890}, {0.996,
			2.65206980790220}, {0.997, 2.74778138544499}, {0.998,
			2.87816173909548}, {0.999, 3.09023230616781}};

	/**
	 * Private constructor. Makes it impossible to instantiate.
	 */
	private InverseStdNormalCDF() {
	}

	/**
	 * Returns quantile value of the standard normal distribution for given probability.
	 *
	 * @param prob desired probability
	 * @return value q such that P(Z &le; q) = prob where Z is a random variable with standard normal distribution
	 * @throws IllegalArgumentException if prob is not between 0 and 1
	 */
	public static double getQuantile(double prob) {
		// Check argument
		if (prob < 0.0 || prob > 1.0) {
			throw new IllegalArgumentException("Quantile should be between 0 and 1.");
		}

		// Normal distribution is symmetric
		int sign = 1;
		if (prob < 0.5) {
			prob = 1 - prob;
			sign = -1;
		}

		// TODO : Handle tail of CDF correctly

		if (prob >= 0.999) {
			return invStdNormalCDF[499][1];
		}

		// Compute lower array index and return linear interpolation between the two precomputed values
		int index = ((int) Math.floor(prob * 1000)) - 500;
		return sign * invStdNormalCDF[index][1] + 1000.0 * (invStdNormalCDF[index + 1][1] - invStdNormalCDF[index][1]) * (prob - invStdNormalCDF[index][0]);
	}
}
